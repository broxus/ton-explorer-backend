package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigWorkchainFormat(
    vmVersion: Int,
    vmMode: BigDecimal
)

object ConfigWorkchainFormat {
  def apply(s: TonApi.LiteServerConfigWorkchainFormat): ConfigWorkchainFormat =
    ConfigWorkchainFormat(
      vmVersion = s.vmVersion,
      vmMode = s.vmMode
    )
}
