package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigParamLimits(
    underload: Int,
    softLimit: Int,
    hardLimit: Int
)

object ConfigParamLimits {
  def apply(s: TonApi.LiteServerConfigParamLimits): ConfigParamLimits =
    ConfigParamLimits(
      underload = s.underload,
      softLimit = s.softLimit,
      hardLimit = s.hardLimit
    )
}
