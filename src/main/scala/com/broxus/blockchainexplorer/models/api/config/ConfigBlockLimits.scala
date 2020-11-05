package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigBlockLimits(
    bytes: ConfigParamLimits,
    gas: ConfigParamLimits,
    ltDelta: ConfigParamLimits
)

object ConfigBlockLimits {
  def apply(s: TonApi.LiteServerConfigBlockLimits): ConfigBlockLimits =
    ConfigBlockLimits(
      bytes = ConfigParamLimits(s.bytes),
      gas = ConfigParamLimits(s.gas),
      ltDelta = ConfigParamLimits(s.ltDelta)
    )
}
