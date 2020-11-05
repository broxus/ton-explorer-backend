package com.broxus.blockchainexplorer.indexer

import com.broxus.blockchainexplorer.config.{ IndexerConfig, IndexerConfigProvider }
import com.broxus.blockchainexplorer.repository.{ DBAccountRepository, _ }
import com.broxus.blockchainexplorer.ton.TonService
import zio.clock.Clock
import zio.logging.{ Logger, Logging }
import zio.{ ZIO, ZLayer }

object IndexerService {

  sealed trait Service {}

  type LayerIn = Clock
    with Logging
    with TonService
    with DBBlockRepository
    with DBTransactionRepository
    with DBMessageRepository
    with DBAccountRepository
    with IndexerConfigProvider

  val layer: ZLayer[LayerIn, Throwable, IndexerService] = ZLayer.fromManaged(
    for {
      indexerConfig       <- ZIO.service[IndexerConfig].toManaged_
      logger              <- ZIO.service[Logger[String]].toManaged_
      tonService          <- ZIO.service[TonService.Service].toManaged_
      dbBlockRepository   <- ZIO.service[DBBlockRepository.Service].toManaged_
      dbAccountRepository <- ZIO.service[DBAccountRepository.Service].toManaged_
      _                   <- ZIO.service[DBTransactionRepository.Service].toManaged_
      _                   <- ZIO.service[DBMessageRepository.Service].toManaged_
      _                   <- new Indexer(
                               indexerConfig,
                               logger,
                               tonService,
                               dbBlockRepository,
                               dbAccountRepository
                             )
                               .run()
                               .when(indexerConfig.enabled)
                               .tapError { e =>
                                 logger.throwable("Indexer ERROR:", e)
                               }
                               .fork
                               .toManaged_
    } yield new Service {}
  )
}
