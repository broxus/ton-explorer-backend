package com.broxus.blockchainexplorer.search

import com.broxus.blockchainexplorer.repository._
import com.broxus.blockchainexplorer.repository.models.DBBlock
import com.broxus.blockchainexplorer.search.models.SearchResult
import com.broxus.blockchainexplorer.ton.TonService
import com.broxus.ton.TonApi
import zio.clock.Clock
import zio.logging.{ Logger, Logging }
import zio.{ Task, ZIO, ZLayer }

object SearchService {

  trait Service {
    def searchBlocks(query: String): Task[Seq[TonApi.LiteServerBlock]]
    def searchTransactions(query: String): Task[Seq[TonApi.LiteServerTransaction]]
    def searchMessages(query: String, limit: Int): Task[Seq[TonApi.LiteServerMessage]]
  }

  implicit def dbBlockToApiBlockId(dbBlock: DBBlock): TonApi.TonBlockIdExt =
    new TonApi.TonBlockIdExt(dbBlock.workchain, dbBlock.shard, dbBlock.seqno, dbBlock.roothash, dbBlock.filehash)

  val layer: ZLayer[
    Clock with Logging with TonService with DBBlockRepository with DBTransactionRepository with DBMessageRepository,
    Throwable,
    SearchService
  ] = ZLayer.fromManaged(
    for {
      _                     <- ZIO.service[Logger[String]].toManaged_
      tonService            <- ZIO.service[TonService.Service].toManaged_
      blockRepository       <- ZIO.service[DBBlockRepository.Service].toManaged_
      transactionRepository <- ZIO.service[DBTransactionRepository.Service].toManaged_
      messageRepository     <- ZIO.service[DBMessageRepository.Service].toManaged_
    } yield new Service {

      override def searchBlocks(query: String): Task[Seq[TonApi.LiteServerBlock]] =
        for {
          dbBlocks <- blockRepository.search(query)
          result   <- Task.collectAllSuccesses(dbBlocks.map { dbBlock =>
                        tonService.liteServerGetBlock(dbBlock)
                      })
        } yield result

      override def searchMessages(query: String, limit: Int): Task[Seq[TonApi.LiteServerMessage]] =
        for {
          dbMessages <- messageRepository.search(query, limit)
          result     <- Task.collectAllSuccesses(dbMessages.map {
                          case (dbMessage, dbTransaction, dbBlock) =>
                            tonService
                              .liteServerGetOneTransaction(
                                dbBlock,
                                new TonApi.LiteServerAccountId(dbTransaction.workchain, dbTransaction.accountId),
                                dbTransaction.lt
                              )
                              .map {
                                case tx if dbMessage.out =>
                                  tx.outMsgs.find(_.hash.sameElements(dbMessage.bodyHash))
                                case tx                  =>
                                  Option(tx.inMsg)
                              }
                        })
        } yield result.flatten

      override def searchTransactions(query: String): Task[Seq[TonApi.LiteServerTransaction]] =
        for {
          dbTransactionOpt <- transactionRepository.search(query)
          result           <- Task.foreach(dbTransactionOpt.toSeq) {
                                case (dbTransaction, dbBlock) =>
                                  tonService.liteServerGetOneTransaction(
                                    dbBlock,
                                    new TonApi.LiteServerAccountId(dbTransaction.workchain, dbTransaction.accountId),
                                    dbTransaction.lt
                                  )
                              }
        } yield result
    }
  )
}
