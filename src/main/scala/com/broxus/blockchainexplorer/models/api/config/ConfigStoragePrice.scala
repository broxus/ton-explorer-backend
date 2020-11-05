package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigStoragePrice(
    utimeSince: Int,
    bitPrice: BigDecimal,
    cellPrice: BigDecimal,
    mcBitPrice: BigDecimal,
    mcCellPrice: BigDecimal
)

object ConfigStoragePrice {
  def apply(s: TonApi.LiteServerConfigStoragePrice): ConfigStoragePrice =
    ConfigStoragePrice(
      utimeSince = s.utimeSince,
      bitPrice = s.bitPricePs,
      cellPrice = s.cellPricePs,
      mcBitPrice = s.mcBitPricePs,
      mcCellPrice = s.mcCellPricePs
    )
}
