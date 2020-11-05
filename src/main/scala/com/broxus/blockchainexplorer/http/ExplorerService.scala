package com.broxus.blockchainexplorer.http

import cats.syntax.all._
import com.broxus.blockchainexplorer.config.{ BotConfig, BotConfigProvider }
import com.broxus.blockchainexplorer.http.HttpService.RoutesService
import com.broxus.blockchainexplorer.models.api.common.ApiError
import com.broxus.blockchainexplorer.models.auth.UserProfile
import com.broxus.blockchainexplorer.repository._
import com.broxus.blockchainexplorer.search._
import com.broxus.blockchainexplorer.stats.StatsService
import com.broxus.blockchainexplorer.ton.TonService
import org.http4s.HttpRoutes
import sttp.model.CookieValueWithMeta
import sttp.tapir.server.http4s._
import sttp.tapir.swagger.http4s.SwaggerHttp4s
import sttp.tapir.ztapir.{ ZServerEndpoint, _ }
import zio.interop.catz._
import zio.logging.{ Logger, Logging }
import zio.{ Runtime, Task, ZEnv, ZIO, ZLayer }

object ExplorerService {

  sealed trait Service extends RoutesService[Task]

  def generalRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.getTimeEndpoint.zServerLogic(_ => serverLogic.getTime),
      ExplorerEndpoints.blockchainStatsEndpoint.zServerLogic(_ => serverLogic.blockchainStats),
      ExplorerEndpoints.searchEndpoint.zServerLogic(serverLogic.search),
      AuthEndpoints.authInfoEndpoint.zServerLogic(serverLogic.authInfo)
    )

  def accountRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.getAccountEndpoint.zServerLogic(serverLogic.getAccount),
      ExplorerEndpoints.getAccountTransactionsEndpoint.zServerLogic(serverLogic.getAccountTransactions),
      ExplorerEndpoints.listAccountsEndpoint.zServerLogic(serverLogic.listAccounts)
    )

  def transactionRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.getTransactionEndpoint.zServerLogic(serverLogic.getTransaction),
      ExplorerEndpoints.listTransactionsEndpoint.zServerLogic(serverLogic.listTransactions),
      ExplorerEndpoints.countTransactionsEndpoint.zServerLogic(serverLogic.countTransactions)
    )

  def messageRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.getMessageEndpoint.zServerLogic(serverLogic.getMessage),
      ExplorerEndpoints.listMessagesEndpoint.zServerLogic(serverLogic.listMessages),
      ExplorerEndpoints.countMessagesEndpoint.zServerLogic(serverLogic.countMessages),
      ExplorerEndpoints.searchMessagesEndpoint.zServerLogic(serverLogic.searchMessages)
    )

  def blockRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.getBlockEndpoint.zServerLogic(serverLogic.getBlock),
      ExplorerEndpoints.getBlockBySeqnoEndpoint.zServerLogic(serverLogic.getBlockBySeqno),
      ExplorerEndpoints.getBlockLatestEndpoint.zServerLogic(_ => serverLogic.getBlockLatest),
      ExplorerEndpoints.getZeroState.zServerLogic(_ => serverLogic.getZeroState),
      ExplorerEndpoints.listBlocksEndpoint.zServerLogic(serverLogic.listBlocks)
    )

  def stakeTransactionRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.listStakeTransactionsEndpoint.zServerLogic(serverLogic.listStakeTransactions),
      ExplorerEndpoints.listStakeTransactionsElectionStakesEndpoint.zServerLogic(serverLogic.listElectionStakes),
      ExplorerEndpoints.listTotalStakesEndpoint.zServerLogic(serverLogic.listTotalStakes),
      ExplorerEndpoints.countStakeTransactionsEndpoint.zServerLogic(serverLogic.countStakeTransactions),
      ExplorerEndpoints.getValidatorsAccounts.zServerLogic(serverLogic.getValidatorsAccountsByPublicKeys),
      ExplorerEndpoints.electionDatesEndpoint.zServerLogic(serverLogic.electionDates)
    )

  def validatorRoutes(serverLogic: ExplorerServerLogic): List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]] =
    List(
      ExplorerEndpoints.getValidatorsPastElectionsEndpoint.zServerLogic(_ => serverLogic.getPastElections),
      ExplorerEndpoints.getValidatorsStateEndpoint.zServerLogic(_ => serverLogic.getValidatorsState)
    )

  def authorizeRedirect(
      serverLogic: ExplorerServerLogic
  ): ZServerEndpoint[Any, Option[UserProfile], String, CookieValueWithMeta] =
    AuthEndpoints.authorizeRedirect.zServerLogic(serverLogic.authorizeRedirect)

  def makeRoutes(serverLogic: ExplorerServerLogic): HttpRoutes[Task] = {

    implicit val runtime: Runtime[ZEnv] = Runtime.default

    val endpoints = List[ExplorerServerLogic => List[ZServerEndpoint[Any, _, ApiError, _ <: Equals]]](
      generalRoutes,
      accountRoutes,
      transactionRoutes,
      messageRoutes,
      blockRoutes,
      stakeTransactionRoutes,
      validatorRoutes
    )

    val serverEndpoints = endpoints.flatMap(f => f(serverLogic))

    val routes: List[HttpRoutes[Task]]      = serverEndpoints.map(_.toRoutes)
    val authRedirectRoute: HttpRoutes[Task] = authorizeRedirect(serverLogic).toRoutes

    val swaggerYaml: String = {
      import sttp.tapir.docs.openapi._
      import sttp.tapir.openapi.circe.yaml._

      serverEndpoints
        .toOpenAPI("The blockchain explorer backend", "1.0")
        .toYaml
    }

    routes.reduce(_ <+> _) <+> authRedirectRoute <+> new SwaggerHttp4s(swaggerYaml).routes[Task]
  }

  val layer: ZLayer[
    TonService with BotConfigProvider with Logging with SearchService with StatsService with DBTgUserRepository with DBBlockRepository with DBTransactionRepository with DBMessageRepository with DBAccountRepository with DBStakeTransactionRepository,
    Throwable,
    ExplorerService
  ] = ZLayer.fromManaged {
    for {
      logger                       <- ZIO.service[Logger[String]].toManaged_
      botConfig                    <- ZIO.service[BotConfig].toManaged_
      tonService                   <- ZIO.service[TonService.Service].toManaged_
      searchService                <- ZIO.service[SearchService.Service].toManaged_
      statsService                 <- ZIO.service[StatsService.Service].toManaged_
      dbBlockRepository            <- ZIO.service[DBBlockRepository.Service].toManaged_
      dbTransactionRepository      <- ZIO.service[DBTransactionRepository.Service].toManaged_
      dbMessageRepository          <- ZIO.service[DBMessageRepository.Service].toManaged_
      dbAccountRepository          <- ZIO.service[DBAccountRepository.Service].toManaged_
      dbStakeTransactionRepository <- ZIO.service[DBStakeTransactionRepository.Service].toManaged_
      dbTgUserRepository           <- ZIO.service[DBTgUserRepository.Service].toManaged_
      serverLogic                  <- Task {
                                        new ExplorerServerLogic(
                                          botConfig,
                                          logger,
                                          tonService,
                                          statsService,
                                          dbTgUserRepository,
                                          dbBlockRepository,
                                          dbTransactionRepository,
                                          dbStakeTransactionRepository,
                                          dbMessageRepository,
                                          dbAccountRepository
                                        )
                                      }.toManaged_
      serviceRoutes                <- ZIO(makeRoutes(serverLogic)).toManaged_
    } yield new Service {
      val routes: HttpRoutes[Task] = serviceRoutes
    }
  }
}
