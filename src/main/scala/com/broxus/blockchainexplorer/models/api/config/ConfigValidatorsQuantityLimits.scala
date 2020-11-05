package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigValidatorsQuantityLimits(
    maxValidators: Int,
    maxMainValidators: Int,
    minValidators: Int
)

object ConfigValidatorsQuantityLimits {
  def apply(s: TonApi.LiteServerConfigValidatorsQuantityLimits): ConfigValidatorsQuantityLimits =
    ConfigValidatorsQuantityLimits(
      maxValidators = s.maxValidators,
      maxMainValidators = s.maxMainValidators,
      minValidators = s.minValidators
    )
}
