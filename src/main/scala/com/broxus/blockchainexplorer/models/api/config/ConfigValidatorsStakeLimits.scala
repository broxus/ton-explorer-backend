package com.broxus.blockchainexplorer.models.api.config

import java.math.BigInteger

import com.broxus.ton.TonApi

case class ConfigValidatorsStakeLimits(
    minStake: BigDecimal,
    maxStake: BigDecimal,
    minTotalStake: BigDecimal,
    maxStakeFactor: Int
)

object ConfigValidatorsStakeLimits {
  def apply(s: TonApi.LiteServerConfigValidatorsStakeLimits): ConfigValidatorsStakeLimits =
    ConfigValidatorsStakeLimits(
      minStake = BigDecimal(new BigInteger(s.minStake)),
      maxStake = BigDecimal(new BigInteger(s.maxStake)),
      minTotalStake = BigDecimal(new BigInteger(s.minTotalStake)),
      maxStakeFactor = s.maxStakeFactor
    )
}
