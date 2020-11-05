package com.broxus.blockchainexplorer.models.api.common

import com.broxus.ton.TonApi

case class GlobalVersion(
    version: Int,
    capabilities: BigDecimal
)

object GlobalVersion {
  def apply(s: TonApi.LiteServerGlobalVersion): GlobalVersion =
    GlobalVersion(
      version = s.version,
      capabilities = s.capabilities
    )
}
