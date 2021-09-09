package com.broxus.blockchainexplorer

import com.broxus.blockchainexplorer.config._
import com.broxus.blockchainexplorer.http.{ ExplorerService, HealthzService, HttpService }
import com.broxus.blockchainexplorer.indexer.IndexerService
import com.broxus.blockchainexplorer.repository.db.{
  DBAccountRepositoryImpl,
  DBBlockRepositoryImpl,
  DBMessageRepositoryImpl,
  DBStakeTransactionRepositoryImpl,
  DBTgUserRepositoryImpl,
  DBTransactionRepositoryImpl,
  DBTransactor
}
import com.broxus.blockchainexplorer.repository.{
  DBAccountRepository,
  DBBlockRepository,
  DBMessageRepository,
  DBStakeTransactionRepository,
  DBTgUserRepository,
  DBTransactionRepository
}
import com.broxus.blockchainexplorer.search.SearchService
import com.broxus.blockchainexplorer.stats.StatsService
import com.broxus.blockchainexplorer.ton.TonService
import zio.ZLayer
import zio.blocking.Blocking
import zio.clock.Clock
import zio.logging.Logging
import zio.logging.slf4j.Slf4jLogger
object Layers {

  type Layer0Env = ConfigProvider with Logging with Blocking with Clock
  type Layer1Env = Layer0Env
    with BotConfigProvider
    with TonConfigProvider
    with DBConfigProvider
    with IndexerConfigProvider
    with StatsConfigProvider
  type Layer2Env = Layer1Env with TonService with DBTransactor
  type Layer3Env = Layer2Env
    with DBBlockRepository
    with DBTransactionRepository
    with DBMessageRepository
    with DBAccountRepository
    with DBStakeTransactionRepository
    with DBTgUserRepository
  type Layer4Env = Layer3Env with IndexerService with SearchService with StatsService
  type Layer5Env = Layer4Env with HealthzService with ExplorerService
  type Layer6Env = Layer5Env with HttpService

  type AppEnv = Layer6Env

  object live {

    val layer0: ZLayer[Blocking with Clock, Throwable, Layer0Env] =
      Blocking.any ++ Clock.any ++ ConfigProvider.live ++ Slf4jLogger.make((_, msg) => msg)

    val layer1: ZLayer[Layer0Env, Throwable, Layer1Env] =
      DBConfigProvider.fromConfig ++ BotConfigProvider.fromConfig ++ TonConfigProvider.fromConfig ++ IndexerConfigProvider.fromConfig ++ StatsConfigProvider.fromConfig ++ ZLayer.identity

    val layer2: ZLayer[Layer1Env, Throwable, Layer2Env] =
      TonService.layer ++ DBTransactor.layer ++ ZLayer.identity

    val layer3: ZLayer[Layer2Env, Throwable, Layer3Env] =
      DBBlockRepositoryImpl.layer ++
        DBTransactionRepositoryImpl.layer ++
        DBMessageRepositoryImpl.layer ++
        DBAccountRepositoryImpl.layer ++
        DBStakeTransactionRepositoryImpl.layer ++
        DBTgUserRepositoryImpl.layer ++
        ZLayer.identity

    val layer4: ZLayer[Layer3Env, Throwable, Layer4Env] =
      StatsService.layer ++ SearchService.layer ++ IndexerService.layer ++ ZLayer.identity

    val layer5: ZLayer[Layer4Env, Throwable, Layer5Env] =
      HealthzService.layer ++ ExplorerService.layer ++ ZLayer.identity

    val layer6: ZLayer[Layer5Env, Throwable, AppEnv] =
      HttpService.layer ++ ZLayer.identity

    val appLayer: ZLayer[Blocking with Clock, Throwable, AppEnv] =
      layer0 >>> layer1 >>> layer2 >>> layer3 >>> layer4 >>> layer5 >>> layer6

  }

}
