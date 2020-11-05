package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigValidatorsTimings(
    validatorsElectedFor: Int,
    electionsStartBefore: Int,
    electionsEndBefore: Int,
    stakeHeldFor: Int
)

object ConfigValidatorsTimings {
  def apply(s: TonApi.LiteServerConfigValidatorsTimings): ConfigValidatorsTimings =
    ConfigValidatorsTimings(
      validatorsElectedFor = s.validatorsElectedFor,
      electionsStartBefore = s.electionsStartBefore,
      electionsEndBefore = s.electionsEndBefore,
      stakeHeldFor = s.stakeHeldFor
    )

}
