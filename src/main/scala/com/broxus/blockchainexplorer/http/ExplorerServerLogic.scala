package com.broxus.blockchainexplorer.http

import cats.data.NonEmptyList
import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.config.BotConfig
import com.broxus.blockchainexplorer.models.api.account._
import com.broxus.blockchainexplorer.models.api.blocks._
import com.broxus.blockchainexplorer.models.api.common._
import com.broxus.blockchainexplorer.models.api.config.{
  BlockchainConfig,
  ConfigValidatorsQuantityLimits,
  ConfigValidatorsStakeLimits,
  ConfigValidatorsTimings
}
import com.broxus.blockchainexplorer.models.api.message.{
  Message,
  MessageListItem,
  MessagesCountRequest,
  MessagesRequest
}
import com.broxus.blockchainexplorer.models.api.search.{ SearchRequest, SearchResponse, SearchResponseMessageItem }
import com.broxus.blockchainexplorer.models.api.stats.{ BlockchainStats, GetTimeResponse }
import com.broxus.blockchainexplorer.models.api.transactions.{
  Transaction,
  TransactionListItem,
  TransactionsCountRequest,
  TransactionsRequest
}
import com.broxus.blockchainexplorer.models.api.validator._
import com.broxus.blockchainexplorer.models.auth.{ AuthInfo, UserInfo, UserProfile }
import com.broxus.blockchainexplorer.repository.models.{ DBStakeTransaction, TransactionType }
import com.broxus.blockchainexplorer.repository.{ DBStakeTransactionRepository, _ }
import com.broxus.blockchainexplorer.stats.StatsService
import com.broxus.blockchainexplorer.ton.{ TonApiErrorException, TonService }
import com.broxus.blockchainexplorer.utils.Encryption
import com.broxus.ton.TonApi
import com.broxus.ton.TonApi.TonBlockIdExt
import sttp.model.CookieValueWithMeta
import zio.logging.Logger
import zio.{ Runtime, Task, ZEnv, ZIO }

import scala.util.Try

class ExplorerServerLogic(
    botConfig: BotConfig,
    logger: Logger[String],
    tonService: TonService.Service,
    statsService: StatsService.Service,
    dbTgUserRepository: DBTgUserRepository.Service,
    dbBlockRepository: DBBlockRepository.Service,
    dbTransactionRepository: DBTransactionRepository.Service,
    dbStakeTransactionRepository: DBStakeTransactionRepository.Service,
    dbMessageRepository: DBMessageRepository.Service,
    dbAccountRepository: DBAccountRepository.Service
) {

  implicit val runtime: Runtime[ZEnv] = Runtime.default
  private val notAuthError            = ApiError("not authorized")

  def toApiError(e: Throwable): ApiError =
    e match {
      case e: TonApiErrorException => ApiError(e.error.message)
      case NotAuthorizedException  => notAuthError
      case e                       => ApiError("Internal server error: " + e.getMessage)
    }

  def getTime: ZIO[Any, ApiError, GetTimeResponse] =
    tonService
      .liteServerGetTime()
      .bimap(toApiError, e => GetTimeResponse(e.now, (System.currentTimeMillis() / 1000).toInt))

  def getAccount(rq: IdRequest): ZIO[Any, ApiError, Account] =
    for {
      masterchainInfo <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      unpackedAddress <- tonService.unpackAccountAddress(rq.id).mapError(toApiError)
      result          <- tonService
                           .liteServerGetAccountState(
                             masterchainInfo.last,
                             new TonApi.LiteServerAccountId(unpackedAddress.workchainId, unpackedAddress.addr)
                           )
                           .bimap(toApiError, Account(_))
    } yield result

  def getAccountTransactions(rq: AccountTransactionsRequest): ZIO[Any, ApiError, List[Transaction]] =
    for {
      accountState    <- tonService.getAccountState(rq.account).mapError(toApiError)
      unpackedAddress <- tonService.unpackAccountAddress(accountState.address.accountAddress).mapError(toApiError)
      result          <- tonService
                           .liteServerGetAccountTransactions(
                             new TonApi.LiteServerAccountId(unpackedAddress.workchainId, unpackedAddress.addr),
                             rq.from.fold(accountState.lastTransactionId.lt)(_.lt.toLong),
                             rq.from.fold(accountState.lastTransactionId.hash)(e => HexHelper.convertHexToBytes(e.hash)),
                             rq.count
                           )
                           .bimap(
                             toApiError,
                             r => r.transactions.toList.map(item => Transaction(item, None))
                           )
    } yield result

  def getMessage(rq: LimitedIdRequest): ZIO[Any, ApiError, List[Message]] = {
    val requestedId = HexHelper.convertHexToBytes(rq.id)
    for {
      dbItemsOpt <- dbMessageRepository.search(rq.id, rq.limit).mapError(toApiError)
      result     <- ZIO.foreach(dbItemsOpt) { item =>
                      val (dbMessage, dbTransaction, dbBlock) = item
                      tonService
                        .liteServerGetOneTransaction(
                          new TonBlockIdExt(
                            dbBlock.workchain,
                            dbBlock.shard,
                            dbBlock.seqno,
                            dbBlock.roothash,
                            dbBlock.filehash
                          ),
                          new TonApi.LiteServerAccountId(dbTransaction.workchain, dbTransaction.accountId),
                          dbTransaction.lt
                        )
                        .bimap(
                          toApiError,
                          transaction =>
                            if (!dbMessage.out && transaction.inMsg.hash.sameElements(requestedId))
                              Some(transaction.inMsg)
                            else
                              transaction.outMsgs.find(_.hash.sameElements(requestedId))
                        )
                    }
    } yield result.toList.flatten.map(Message(_))
  }

  def getTransaction(rq: IdRequest): ZIO[Any, ApiError, Transaction] =
    for {
      dbItemsOpt <- dbTransactionRepository.search(rq.id).mapError(toApiError)
      result     <- dbItemsOpt match {
                      case Some((dbTransaction, dbBlock)) =>
                        val tonBlockIdExt = new TonApi.TonBlockIdExt(
                          dbBlock.workchain,
                          dbBlock.shard,
                          dbBlock.seqno,
                          dbBlock.roothash,
                          dbBlock.filehash
                        )
                        tonService
                          .liteServerGetOneTransaction(
                            tonBlockIdExt,
                            new TonApi.LiteServerAccountId(dbTransaction.workchain, dbTransaction.accountId),
                            dbTransaction.lt
                          )
                          .bimap(toApiError, Transaction(_, Some(BlockIdExt(tonBlockIdExt))))
                      case _                              => ZIO.fail(ApiError("index not found"))
                    }
    } yield result

  def getBlock(rq: IdRequest): ZIO[Any, ApiError, Block] =
    for {
      dbItemsOpt <- dbBlockRepository.search(rq.id).mapError(toApiError)
      result     <- dbItemsOpt.headOption match {
                      case Some(dbBlock) =>
                        tonService
                          .liteServerGetBlock(
                            new TonBlockIdExt(
                              dbBlock.workchain,
                              dbBlock.shard,
                              dbBlock.seqno,
                              dbBlock.roothash,
                              dbBlock.filehash
                            )
                          )
                          .bimap(toApiError, Block(_))
                      case _             => ZIO.fail(ApiError("index not found"))
                    }
    } yield result

  def getBlockBySeqno(rq: BlockRequest): ZIO[Any, ApiError, Block] =
    for {
      dbItemsOpt <- dbBlockRepository.search(rq.workchain, rq.shard, rq.seqno).mapError(toApiError)
      result     <- dbItemsOpt match {
                      case Some(dbBlock) =>
                        tonService
                          .liteServerGetBlock(
                            new TonBlockIdExt(
                              dbBlock.workchain,
                              dbBlock.shard,
                              dbBlock.seqno,
                              dbBlock.roothash,
                              dbBlock.filehash
                            )
                          )
                          .bimap(toApiError, Block(_))
                      case _             => ZIO.fail(ApiError("index not found"))
                    }
    } yield result

  def getBlockLatest: ZIO[Any, ApiError, Block] =
    for {
      masterchainInfo <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      result          <- tonService.liteServerGetBlock(masterchainInfo.last).bimap(toApiError, Block(_))
    } yield result

  def getZeroState: ZIO[Any, ApiError, BlockState] =
    for {
      masterchainInfo <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      result          <- tonService
                           .liteServerGetBlockState(
                             new TonBlockIdExt(
                               masterchainInfo.init.workchain,
                               0x8000000000000000L,
                               0,
                               masterchainInfo.init.rootHash,
                               masterchainInfo.init.fileHash
                             )
                           )
                           .bimap(toApiError, BlockState(_))
    } yield result

  def listBlocks(rq: BlocksRequest): ZIO[Any, ApiError, List[BlockListItem]] = {
    val isValid = rq.limit <= 100
    if (isValid)
      for {
        dbItems <- dbBlockRepository.list(rq.limit, rq.offset, rq.fromTs, rq.toTs, rq.skipEmpty).mapError(toApiError)
      } yield dbItems.map(BlockListItem(_)).toList
    else
      ZIO.fail(ApiError("limit must be <= 100"))
  }

  def listTransactions(rq: TransactionsRequest): ZIO[Any, ApiError, List[TransactionListItem]] = {

    val addressBytes = rq
      .account
      .flatMap(account => Try(HexHelper.convertHexToBytes(account.address)).toOption)

    val errors = Seq(
      Some(ApiError("limit must be <= 100")).filterNot(_ => rq.limit <= 100),
      Some(ApiError("invalid account hex address")).filterNot(_ => addressBytes.isEmpty == rq.account.isEmpty),
      Some(ApiError("invalid transaction type")).filterNot(_ =>
        rq.transactionTypes.forall(_.forall(t => TransactionType.fromEnum(t).nonEmpty))
      )
    ).flatten

    if (errors.isEmpty)
      for {
        dbItems <- dbTransactionRepository
                     .list(
                       rq.limit,
                       rq.offset,
                       rq.fromTs,
                       rq.toTs,
                       rq.account.map(e => (e.workchain, addressBytes.get)),
                       rq.excludeAccounts
                         .map(_.flatMap { aId =>
                           Try(HexHelper.convertHexToBytes(aId.address)).toOption.map(b => (aId.workchain, b))
                         }),
                       rq.transactionTypes
                     )
                     .mapError(toApiError)
      } yield dbItems.map(TransactionListItem(_)).toList
    else
      ZIO.fail(errors.head)
  }

  def countTransactions(rq: TransactionsCountRequest): ZIO[Any, ApiError, CountResponse] = {
    val addressBytes = rq
      .account
      .flatMap(account => Try(HexHelper.convertHexToBytes(account.address)).toOption)

    val errors = Seq(
      Some(ApiError("invalid account hex address")).filterNot(_ => addressBytes.isEmpty == rq.account.isEmpty),
      Some(ApiError("invalid transaction type")).filterNot(_ =>
        rq.transactionTypes.forall(_.forall(t => TransactionType.fromEnum(t).nonEmpty))
      )
    ).flatten

    if (errors.isEmpty)
      for {
        c <- dbTransactionRepository
               .count(
                 rq.fromTs,
                 rq.toTs,
                 rq.account.map(e => (e.workchain, addressBytes.get)),
                 rq.excludeAccounts
                   .map(_.flatMap { aId =>
                     Try(HexHelper.convertHexToBytes(aId.address)).toOption.map(b => (aId.workchain, b))
                   }),
                 rq.transactionTypes
               )
               .mapError(toApiError)
      } yield CountResponse(c)
    else
      ZIO.fail(errors.head)
  }

  def listMessages(rq: MessagesRequest): ZIO[Any, ApiError, List[MessageListItem]] = {
    val addressBytes = rq
      .account
      .flatMap(account => Try(HexHelper.convertHexToBytes(account.address)).toOption)

    val errors = Seq(
      Some(ApiError("limit must be <= 100")).filterNot(_ => rq.limit <= 100),
      Some(ApiError("invalid account hex address")).filterNot(_ => addressBytes.isEmpty == rq.account.isEmpty)
    ).flatten

    if (errors.isEmpty)
      for {
        dbItems <- dbMessageRepository
                     .list(
                       rq.limit,
                       rq.offset,
                       rq.fromTs,
                       rq.toTs,
                       rq.account.map(e => (e.workchain, addressBytes.get)),
                       rq.excludeAccounts
                         .map(_.flatMap { aId =>
                           Try(HexHelper.convertHexToBytes(aId.address)).toOption.map(b => (aId.workchain, b))
                         })
                     )
                     .mapError(toApiError)
      } yield dbItems.map(MessageListItem(_)).toList
    else
      ZIO.fail(errors.head)
  }

  def countMessages(rq: MessagesCountRequest): ZIO[Any, ApiError, CountResponse] = {
    val addressBytes = rq
      .account
      .flatMap(account => Try(HexHelper.convertHexToBytes(account.address)).toOption)

    val errors = Seq(
      Some(ApiError("invalid account hex address")).filterNot(_ => addressBytes.isEmpty == rq.account.isEmpty)
    ).flatten

    if (errors.isEmpty)
      for {
        c <- dbMessageRepository
               .count(
                 rq.fromTs,
                 rq.toTs,
                 rq.out,
                 rq.account.map(e => (e.workchain, addressBytes.get)),
                 rq.excludeAccounts
                   .map(_.flatMap { aId =>
                     Try(HexHelper.convertHexToBytes(aId.address)).toOption.map(b => (aId.workchain, b))
                   })
               )
               .mapError(toApiError)
      } yield CountResponse(c)
    else
      ZIO.fail(errors.head)
  }

  def listAccounts(rq: AccountsRequest): ZIO[Any, ApiError, List[AccountListItem]] = {
    val orderColumn = DBAccountColumn.find(rq.orderColumn)
    val errors      = Seq(
      Some(ApiError("limit must be <= 100")).filterNot(_ => rq.limit <= 100),
      Some(ApiError("invalid value for orderColumn")).filterNot(_ => orderColumn.isDefined)
    ).flatten

    if (errors.isEmpty)
      for {
        dbItems <- dbAccountRepository
                     .list(rq.limit, rq.offset, orderColumn.get, rq.asc, rq.fromTs, rq.toTs)
                     .mapError(toApiError)
      } yield dbItems.map(AccountListItem(_)).toList
    else
      ZIO.fail(errors.head)
  }

  def getPastElections: ZIO[Any, ApiError, List[PastElectionListItem]] = {
    val electorAddr = HexHelper.convertHexToBytes("3333333333333333333333333333333333333333333333333333333333333333")
    for {
      state  <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      result <- tonService
                  .liteServerGetPastElections(state.last, electorAddr)
                  .bimap(toApiError, _.elections.map(PastElectionListItem(_)).toList)
    } yield result
  }

  def getValidatorsState: ZIO[Any, ApiError, ValidatorsState] = {
    def loadStakes(electionId: Option[Int]) =
      electionId
        .map(id =>
          dbStakeTransactionRepository
            .listElectionStakes(id)
            .bimap(toApiError, transactions => Some(transactions.map(StakeTransactionListItem(_))))
        )
        .getOrElse(ZIO.none)

    for {
      state          <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      config         <- tonService.liteServerGetConfigAll(state.last).bimap(toApiError, BlockchainConfig(_))
      previousStakes <- loadStakes(config.prevVset.map(_.utimeSince))
      currentStakes  <- loadStakes(config.currVset.map(_.utimeSince))
      nextStakes     <- loadStakes(config.nextVset.map(_.utimeSince).orElse(config.currVset.map(_.utimeUntil)))
    } yield ValidatorsState(config, previousStakes, currentStakes, nextStakes)
  }

  def blockchainStats: ZIO[Any, ApiError, BlockchainStats] =
    Task(statsService.getBlockchainStats).mapError(toApiError)

  def searchMessages(rq: SearchRequest): ZIO[Any, ApiError, List[SearchResponseMessageItem]] =
    dbMessageRepository
      .search(rq.query, 10)
      .bimap(
        toApiError,
        _.map(e =>
          SearchResponseMessageItem(BlockListItem(e._3), TransactionListItem(e._2), MessageListItem(e._1))
        ).toList
      )

  def search(rq: SearchRequest): ZIO[Any, ApiError, SearchResponse] =
    for {
      blocks          <- dbBlockRepository.search(rq.query).bimap(toApiError, _.map(BlockListItem(_)))
      messages        <- searchMessages(rq)
      transaction     <- dbTransactionRepository.search(rq.query).bimap(toApiError, _.map(e => TransactionListItem(e._1)))
      bytesOpt        <- ZIO.succeed(HexHelper.convertHexToBytesSafe(rq.query))
      accountIds      <- bytesOpt match {
                           // TODO: avoid hardcode workchains
                           case Some(bytes) => ZIO.succeed(Seq((-1, bytes), (0, bytes)))
                           case _           =>
                             tonService
                               .unpackAccountAddress(rq.query)
                               .map(u => Seq((u.workchainId, u.addr)))
                               .orElseSucceed(Nil)
                         }
      masterchainInfo <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      accounts        <- ZIO
                           .foreach(accountIds) {
                             case (workchain, address) =>
                               tonService
                                 .liteServerGetAccountState(
                                   masterchainInfo.last,
                                   new TonApi.LiteServerAccountId(workchain, address)
                                 )
                                 .fold(_ => Option.empty[Account], e => Option(Account(e)))
                           }
                           .map(_.flatten)
      validators      <- (accounts, bytesOpt) match {
                           case (Nil, Some(bytes)) =>
                             dbStakeTransactionRepository
                               .getAccountsByPublicKeys(NonEmptyList.one(bytes))
                               .fold(
                                 _ => Seq.empty[AccountId],
                                 items => items.map { case (_, w, a) => AccountId(w, HexHelper.convertBytesToHex(a)) }
                               )
                           case (items, _)         =>
                             ZIO
                               .foreach(items) { a =>
                                 dbStakeTransactionRepository
                                   .count(None, None, Some((a.workchain, HexHelper.convertHexToBytes(a.addressHex))), None)
                                   .fold(
                                     _ => Seq.empty[AccountId],
                                     c => Some(AccountId(a.workchain, a.addressHex)).filter(_ => c > 0)
                                   )
                               }
                               .map(_.flatten.toSeq)
                         }
    } yield SearchResponse(blocks, messages, transaction, accounts, validators.headOption)

  def listElectionStakes(rq: ElectionStakesRequest): ZIO[Any, ApiError, List[StakeTransactionListItem]] =
    dbStakeTransactionRepository
      .listElectionStakes(rq.electionId)
      .bimap(toApiError, _.map(StakeTransactionListItem(_)).toList)

  def listStakeTransactions(rq: StakeTransactionListRequest): ZIO[Any, ApiError, List[StakeTransactionListItem]] = {
    val addressBytes = rq
      .account
      .flatMap(account => Try(HexHelper.convertHexToBytes(account.address)).toOption)

    val errors = Seq(
      Some(ApiError("limit must be <= 10000")).filterNot(_ => rq.limit <= 10000),
      Some(ApiError("invalid account hex address")).filterNot(_ => addressBytes.isEmpty == rq.account.isEmpty)
    ).flatten

    if (errors.isEmpty)
      for {
        items <- dbStakeTransactionRepository
                   .list(
                     rq.limit,
                     rq.offset,
                     rq.fromTs,
                     rq.toTs,
                     rq.account.map(e => (e.workchain, addressBytes.get)),
                     rq.electionTime
                   )
                   .mapError(toApiError)
      } yield items.map(StakeTransactionListItem(_)).toList
    else
      ZIO.fail(errors.head)
  }

  def listTotalStakes(rq: TotalStakesRequest): ZIO[Any, ApiError, Seq[TotalStakeListItem]] = {
    def processTransactions(
        now: Int,
        addr: Array[Byte],
        timings: ConfigValidatorsTimings,
        stakeLimits: ConfigValidatorsStakeLimits,
        quantityLimits: ConfigValidatorsQuantityLimits,
        transactions: Seq[DBStakeTransaction]
    ): Seq[TotalStakeListItem] = {
      val maxStake      = stakeLimits.maxStake.toBigInt
      val minStake      = stakeLimits.minStake.toBigInt
      val minTotalStake = stakeLimits.minTotalStake.toBigInt
      val targetAddr    = HexHelper.convertBytesToHex(addr)

      case class IntermediateStake(
          stake: BigInt,
          maxFactor: Int,
          pubKey: String,
          address: String
      )

      def processElections(
          electionId: Int,
          stakes: Seq[(String, BigDecimal, Int, Int, String)]
      ): Option[TotalStakeListItem] = {
        type Key = (BigInt, Int, String)

        val l = stakes.map { item =>
          val (pubKey, stake, time, maxFactor, addr) = item
          ((stake.toBigInt, -time, pubKey), Math.min(maxFactor, stakeLimits.maxStakeFactor), addr)
        }.sortBy(_._1).reverse.map { item =>
          val ((stake, _, pubKey), maxFactor, addr) = item
          IntermediateStake(stake.min(maxStake), maxFactor, pubKey, addr)
        }

        def computeTotalStake(n: Int, mStake: BigInt): BigInt =
          l.take(n).foldLeft(BigInt(0)) { (totalStake, item) =>
            totalStake + item.stake.min((item.maxFactor * mStake) >> 16)
          }

        val validatorCount    = Math.min(l.length, quantityLimits.maxValidators)
        val minValidatorCount = Math.max(quantityLimits.minValidators, 1)

        Option
          .when(validatorCount >= minValidatorCount) {
            l.drop(minValidatorCount - 1).foldLeft((minValidatorCount - 1, BigInt(0), 0)) { (current, item) =>
              var (i, bestStake, m) = current
              i += 1

              if (item.stake >= minStake) {
                val totalStake = computeTotalStake(i, item.stake)
                if (totalStake > bestStake) {
                  bestStake = totalStake
                  m = i
                }
              }

              (i, bestStake, m)
            }
          }
          .flatMap { item =>
            val (_, bestStake, m) = item
            Option.when(m != 0 && bestStake >= minTotalStake) {
              val minStake = l(m - 1).stake

              var totalStake  = BigInt(0)
              var totalWeight = BigInt(0)

              val electionStakes = l.take(m).flatMap { item =>
                var trueStake = item.stake.min((item.maxFactor * minStake) >> 16)

                val weight = (trueStake << 60) / bestStake
                totalStake += trueStake
                totalWeight += weight

                Option.when(item.address == targetAddr) {
                  ElectionStakeItem(
                    publicKey = item.pubKey,
                    address = item.address,
                    weight = BigDecimal(weight),
                    trueStake = BigDecimal(trueStake),
                    unaccountedStake = BigDecimal(item.stake - trueStake)
                  )
                }
              }

              TotalStakeListItem(
                electionId,
                totalStake = BigDecimal(totalStake),
                totalWeight = BigDecimal(totalWeight),
                stakes = electionStakes
              )
            }
          }
      }

      var currentStakes     = List[(String, BigDecimal, Int, Int, String)]()
      var currentElectionId = 0
      var hasTargetAddress  = false

      transactions
        .flatMap({ item =>
          (
            item.publicKey,
            item.electionTime,
            item.maxFactor
          ) match {
            case (Some(pubKey), Some(electionTime), Some(maxFactor)) =>
              var result: Option[TotalStakeListItem] = None
              if (currentElectionId != electionTime && currentStakes.nonEmpty) {
                if (hasTargetAddress)
                  result = processElections(currentElectionId, currentStakes)

                currentStakes = List()
                hasTargetAddress = false
              }

              val pubKeyStr = HexHelper.convertBytesToHex(pubKey)
              val addrStr   = HexHelper.convertBytesToHex(item.accountAddress)
              hasTargetAddress ||= addrStr == targetAddr

              currentElectionId = electionTime
              currentStakes = currentStakes.appended((pubKeyStr, item.value, item.time, maxFactor, addrStr))
              result
            case _                                                   => None
          }
        })
        .concat(
          Option
            .when(hasTargetAddress && currentElectionId < now + timings.electionsEndBefore && currentStakes.nonEmpty) {
              processElections(currentElectionId, currentStakes)
            }
            .flatten
        )
    }

    val now = java.time.Instant.now().getEpochSecond / 1000

    for {
      unpackedAddress   <- tonService.unpackAccountAddress(rq.address).mapError(toApiError)
      _                 <- ZIO.unless(unpackedAddress.workchainId == -1) {
                             ZIO.fail(ApiError("only accounts in masterchain can be validators"))
                           }
      stakeTransactions <- dbStakeTransactionRepository.listAllStakeTransactions.mapError(toApiError)
      masterchain       <- tonService.liteServerGetMasterchainInfo().mapError(toApiError)
      config            <- tonService.liteServerGetConfigAll(masterchain.last).bimap(toApiError, BlockchainConfig(_))
      result            <- (config.validatorsTimings, config.validatorsStakeLimits, config.validatorsQuantityLimits) match {
                             case (Some(timings), Some(stakeLimits), Some(quantityLimits)) =>
                               ZIO.succeed(
                                 processTransactions(
                                   now.toInt,
                                   unpackedAddress.addr,
                                   timings,
                                   stakeLimits,
                                   quantityLimits,
                                   stakeTransactions
                                 )
                               )
                             case _                                                        => ZIO.fail(ApiError("invalid config"))
                           }
    } yield result
  }

  def countStakeTransactions(rq: StakeTransactionCountRequest): ZIO[Any, ApiError, CountResponse] = {
    val addressBytes = rq
      .account
      .flatMap(account => Try(HexHelper.convertHexToBytes(account.address)).toOption)

    val errors = Seq(
      Some(ApiError("invalid account hex address")).filterNot(_ => addressBytes.isEmpty == rq.account.isEmpty)
    ).flatten

    if (errors.isEmpty)
      for {
        c <- dbStakeTransactionRepository
               .count(
                 rq.fromTs,
                 rq.toTs,
                 rq.account.map(e => (e.workchain, addressBytes.get)),
                 rq.electionTime
               )
               .mapError(toApiError)
      } yield CountResponse(c)
    else
      ZIO.fail(errors.head)
  }

  def getValidatorsAccountsByPublicKeys(rq: ValidatorAccountsRequest): ZIO[Any, ApiError, ValidatorAccountsResponse] = {
    val validKeysOpt =
      NonEmptyList.fromList(rq.publicKeys.flatMap(publicKey => Try(HexHelper.convertHexToBytes(publicKey)).toOption))
    validKeysOpt match {
      case Some(validKeys) =>
        dbStakeTransactionRepository
          .getAccountsByPublicKeys(validKeys)
          .bimap(
            toApiError,
            items =>
              ValidatorAccountsResponse(items.map {
                case (publicKey, workchain, address) =>
                  HexHelper.convertBytesToHex(publicKey) -> AccountId(workchain, HexHelper.convertBytesToHex(address))
              }.toMap)
          )
      case _               =>
        ZIO.fail(ApiError("request haven't valid keys"))
    }
  }

  def electionDates(rq: ElectionDatesRequest) =
    dbStakeTransactionRepository.elections(rq.fromTs, rq.toTs).bimap(toApiError, _.toList)

  def authInfo(sessionOpt: Option[String]): ZIO[Any, ApiError, AuthInfo] =
    sessionOpt.flatMap(session =>
      Try(Encryption.decrypt(botConfig.token.get, session).split("#")(0).toLong).toOption
    ) match {
      case Some(userId) =>
        dbTgUserRepository
          .findUser(userId)
          .bimap(
            toApiError,
            {
              case Some(user) =>
                AuthInfo(
                  auth = true,
                  user = Some(UserInfo(user.id, user.firstName, user.lastName, user.username, user.photoUrl))
                )
              case _          => AuthInfo(auth = false, user = None)
            }
          )
      case None         =>
        ZIO.succeed(AuthInfo(auth = false, user = None))
    }

  def authorizeRedirect(userProfileOpt: Option[UserProfile]): ZIO[Any, String, CookieValueWithMeta] =
    userProfileOpt match {
      case Some(userProfile)
          if botConfig.enabled &&
            botConfig.token.exists(token => UserProfile.isValid(userProfile, token)) =>
        dbTgUserRepository
          .saveAuth(userProfile)
          .bimap(
            _.getLocalizedMessage,
            _ =>
              CookieValueWithMeta(
                Encryption.encrypt(botConfig.token.get, s"${userProfile.id}#${System.currentTimeMillis()}"),
                None,
                None,
                botConfig.domain,
                Some("/"),
                secure = true,
                httpOnly = true,
                Map.empty
              )
          )
      case _ =>
        ZIO.succeed(
          CookieValueWithMeta("", None, None, botConfig.domain, Some("/"), secure = true, httpOnly = true, Map.empty)
        )
    }
}

case object NotAuthorizedException extends Throwable
