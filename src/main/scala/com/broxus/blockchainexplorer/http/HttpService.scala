package com.broxus.blockchainexplorer.http
import cats.syntax.all._
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.CORS
import zio.clock.Clock
import zio.interop.catz._
import zio.interop.catz.implicits._
import zio.{ Task, ZIO, ZLayer }
import scala.concurrent.duration._

object HttpService {

  sealed trait Service      {}
  trait RoutesService[T[_]] { def routes: HttpRoutes[T] }

  val layer: ZLayer[Clock with HealthzService with ExplorerService, Throwable, HttpService] = ZLayer.fromManaged {
    for {
      healthzRoutes  <- ZIO.service[HealthzService.Service].toManaged_
      explorerRoutes <- ZIO.service[ExplorerService.Service].toManaged_
      _              <- ZIO
                          .runtime[Clock]
                          .flatMap { implicit rts =>
                            BlazeServerBuilder[Task](rts.platform.executor.asEC)
                              .withResponseHeaderTimeout(30.seconds)
                              .bindHttp(9000, "0.0.0.0")
                              .withHttpApp(
                                CORS(
                                  Router("/" -> (explorerRoutes.routes <+> healthzRoutes.routes)).orNotFound
                                )
                              )
                              .serve
                              .compile
                              .drain
                          }
                          .toManaged_
    } yield new Service {}
  }

}
