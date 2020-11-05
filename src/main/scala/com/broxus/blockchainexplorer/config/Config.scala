package com.broxus.blockchainexplorer.config

import pureconfig.generic.semiauto.deriveConvert
import pureconfig.{ ConfigConvert, ConfigSource }
import zio.{ Has, ZIO, ZLayer }

final case class Config(dbConfig: DBConfig, ton: TonConfig, indexer: IndexerConfig, bot: BotConfig)
final case class TonConfig(liteClient: String, useNetworkCallback: Boolean, blockchainName: String, verbosityLevel: Int)
final case class DBConfig(url: String, driver: String, user: String, password: String)
final case class IndexerConfig(enabled: Boolean, zeroState: Seq[IndexerZeroState], stepSize: Int, threadsPerShard: Int)
final case class IndexerZeroState(workchain: Int, shard: Long, seqno: Int)
final case class BotConfig(
    enabled: Boolean,
    id: Option[Long],
    name: Option[String],
    token: Option[String],
    domain: Option[String]
)

object Config           { implicit val convert: ConfigConvert[Config] = deriveConvert           }
object TonConfig        { implicit val convert: ConfigConvert[TonConfig] = deriveConvert        }
object DBConfig         { implicit val convert: ConfigConvert[DBConfig] = deriveConvert         }
object IndexerConfig    { implicit val convert: ConfigConvert[IndexerConfig] = deriveConvert    }
object IndexerZeroState { implicit val convert: ConfigConvert[IndexerZeroState] = deriveConvert }
object BotConfig        { implicit val convert: ConfigConvert[BotConfig] = deriveConvert        }

object ConfigProvider {

  val live: ZLayer[Any, IllegalStateException, Has[Config]] =
    ZIO
      .fromEither(ConfigSource.default.load[Config])
      .mapError(failures =>
        new IllegalStateException(
          s"Error loading configuration: $failures"
        )
      )
      .toLayer
}

object TonConfigProvider {
  val fromConfig: ZLayer[ConfigProvider, Throwable, TonConfigProvider] =
    ZLayer.fromService(_.ton)
}

object DBConfigProvider {
  val fromConfig: ZLayer[ConfigProvider, Throwable, DBConfigProvider] =
    ZLayer.fromService(_.dbConfig)
}

object IndexerConfigProvider {
  val fromConfig: ZLayer[ConfigProvider, Throwable, IndexerConfigProvider] =
    ZLayer.fromService(_.indexer)
}

object BotConfigProvider {
  val fromConfig: ZLayer[ConfigProvider, Throwable, BotConfigProvider] =
    ZLayer.fromService(_.bot)
}
