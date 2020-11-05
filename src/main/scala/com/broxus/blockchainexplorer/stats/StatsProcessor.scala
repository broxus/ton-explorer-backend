package com.broxus.blockchainexplorer.stats

import com.broxus.blockchainexplorer.models.api.stats
import com.broxus.blockchainexplorer.models.api.stats.{ BlockchainStats, BlockchainStatsCounts }
import com.broxus.blockchainexplorer.repository.{
  DBAccountRepository,
  DBBlockRepository,
  DBMessageRepository,
  DBTransactionRepository
}
import zio.logging.Logger
import zio.{ Runtime, Task, ZEnv, ZIO }
import java.util.concurrent.atomic.AtomicReference

class StatsProcessor(
    logger: Logger[String],
    dbBlockRepository: DBBlockRepository.Service,
    dbTransactionRepository: DBTransactionRepository.Service,
    dbMessageRepository: DBMessageRepository.Service,
    dbAccountRepository: DBAccountRepository.Service
) {

  implicit val runtime: Runtime[ZEnv] = Runtime.default

  private val value: AtomicReference[BlockchainStats] = new AtomicReference(BlockchainStats(0, 0, 0, Map.empty))

  def run(): ZIO[Any, Throwable, Unit] =
    for {
      _ <- logger.info(s"Starting StatsProcessor")
      _ <- updateCycle().fork
    } yield ()

  def updateCycle(): ZIO[Any, Throwable, Unit] =
    for {
      t0    <- Task(System.currentTimeMillis())
      _     <- logger.info(s"StatsProcessor: start next cycle")
      stats <- blockchainStats
      t1    <- Task(System.currentTimeMillis())
      _     <- logger.info(s"StatsProcessor: cycle ends in ${t1 - t0} ms")
      _     <- Task(value.set(stats))
      _     <- Task(Thread.sleep(5000))
      _     <- updateCycle()
    } yield ()

  def getValue(): BlockchainStats = value.get()

  private def blockchainStats: ZIO[Any, Throwable, BlockchainStats] =
    for {
      maxSeqno        <- dbBlockRepository.maxSeqno()
      now              = (System.currentTimeMillis() / 1000).toInt
      tsMap            = Map(
                           "H1"    -> Option(now - 3600),
                           "D1"    -> Option(now - 86400),
                           //                             "D7"    -> Option(now - 604800),
                           "TOTAL" -> None
                         )
      totalSupply     <- dbAccountRepository.totalSupply()
      volume24        <- dbMessageRepository.volume(Some(now - 86400))
      transactions    <- ZIO
                           .foreach(tsMap) { (k, v) =>
                             dbTransactionRepository.count(v, None, None, None, None).map(e => k -> e)
                           }
      messages        <- ZIO
                           .foreach(tsMap) { (k, v) =>
                             dbMessageRepository.count(v, None, None, None, None).map(e => k -> e)
                           }
      accountsCreated <- ZIO
                           .foreach(tsMap) { (k, v) =>
                             dbAccountRepository.countCreated(v).map(e => k -> e)
                           }
      accountsUpdated <- ZIO
                           .foreach(tsMap) { (k, v) =>
                             dbAccountRepository.countUpdated(v).map(e => k -> e)
                           }
    } yield stats.BlockchainStats(
      maxSeqno = maxSeqno,
      totalSupply = totalSupply,
      volume24 = volume24,
      counts = tsMap
        .keySet
        .map(k => k -> BlockchainStatsCounts(now, accountsCreated(k), accountsUpdated(k), transactions(k), messages(k)))
        .toMap
    )

}
