package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigMsgForwardPrices(
    lumpPrice: BigDecimal,
    bitPrice: BigDecimal,
    cellPrice: BigDecimal,
    ihrPriceFactor: BigDecimal,
    firstFrac: Int,
    nextFrac: Int
)

object ConfigMsgForwardPrices {
  def apply(s: TonApi.LiteServerConfigMsgForwardPrices): ConfigMsgForwardPrices =
    ConfigMsgForwardPrices(
      lumpPrice = s.lumpPrice,
      bitPrice = s.bitPrice,
      cellPrice = s.cellPrice,
      ihrPriceFactor = s.ihrPriceFactor,
      firstFrac = s.firstFrac,
      nextFrac = s.nextFrac
    )
}
