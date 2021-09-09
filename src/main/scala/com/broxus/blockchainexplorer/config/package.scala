package com.broxus.blockchainexplorer

import zio.Has

package object config {

  type ConfigProvider        = Has[Config]
  type TonConfigProvider     = Has[TonConfig]
  type DBConfigProvider      = Has[DBConfig]
  type IndexerConfigProvider = Has[IndexerConfig]
  type StatsConfigProvider   = Has[StatsConfig]
  type BotConfigProvider     = Has[BotConfig]

}
