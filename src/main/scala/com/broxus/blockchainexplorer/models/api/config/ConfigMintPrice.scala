package com.broxus.blockchainexplorer.models.api.config

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class ConfigMintPrice(
    mintNewPrice: String,
    mintAddPrice: String
)

object ConfigMintPrice {
  def apply(s: TonApi.LiteServerConfigMintPrice): ConfigMintPrice =
    ConfigMintPrice(
      mintNewPrice = HexHelper.convertBytesToHex(s.mintNewPrice),
      mintAddPrice = HexHelper.convertBytesToHex(s.mintAddPrice)
    )
}
