package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class CatchainConfig(
    flags: Option[Int] = None,
    shuffleMcValidators: Option[Boolean] = None,
    mcCatchainLifetime: Int,
    shardCatchainLifetime: Int,
    shardValidatorsLifetime: Int,
    shardValidatorsNum: Int
)

object CatchainConfig {
  def apply: TonApi.LiteServerConfigCatchainConfig => CatchainConfig = {
    case s: TonApi.LiteServerConfigCatchainConfigRegular =>
      CatchainConfig(
        mcCatchainLifetime = s.mcCatchainLifetime,
        shardCatchainLifetime = s.shardCatchainLifetime,
        shardValidatorsLifetime = s.shardValidatorsLifetime,
        shardValidatorsNum = s.shardValidatorsNum
      )
    case s: TonApi.LiteServerConfigCatchainConfigNew     =>
      CatchainConfig(
        flags = Some(s.flags),
        shuffleMcValidators = Some(s.shuffleMcValidators),
        mcCatchainLifetime = s.mcCatchainLifetime,
        shardCatchainLifetime = s.shardCatchainLifetime,
        shardValidatorsLifetime = s.shardValidatorsLifetime,
        shardValidatorsNum = s.shardValidatorsNum
      )
  }
}
