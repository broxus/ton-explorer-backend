package com.broxus.blockchainexplorer.stats

import com.broxus.blockchainexplorer.indexer.IndexerService
import com.broxus.blockchainexplorer.models.api.stats.BlockchainStats
import com.broxus.blockchainexplorer.repository.{
  DBAccountRepository,
  DBBlockRepository,
  DBMessageRepository,
  DBTransactionRepository
}
import zio.clock.Clock
import zio.logging.{ Logger, Logging }
import zio.{ ZIO, ZLayer }

object StatsService {
  trait Service {
    def getBlockchainStats: BlockchainStats
  }

  type LayerIn = Clock
    with Logging
    with DBBlockRepository
    with DBTransactionRepository
    with DBMessageRepository
    with DBAccountRepository

  val layer: ZLayer[LayerIn, Throwable, StatsService] = ZLayer.fromManaged(
    for {
      logger                  <- ZIO.service[Logger[String]].toManaged_
      dbBlockRepository       <- ZIO.service[DBBlockRepository.Service].toManaged_
      dbTransactionRepository <- ZIO.service[DBTransactionRepository.Service].toManaged_
      dbMessageRepository     <- ZIO.service[DBMessageRepository.Service].toManaged_
      dbAccountRepository     <- ZIO.service[DBAccountRepository.Service].toManaged_
      processor                = new StatsProcessor(
                                   logger,
                                   dbBlockRepository,
                                   dbTransactionRepository,
                                   dbMessageRepository,
                                   dbAccountRepository
                                 )
      _                       <- processor
                                   .run()
                                   .tapError { e =>
                                     logger.throwable("Stats processing ERROR:", e)
                                   }
                                   .toManaged_
    } yield new Service {
      override def getBlockchainStats: BlockchainStats = processor.getValue()
    }
  )
}
