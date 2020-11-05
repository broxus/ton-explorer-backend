package com.broxus.blockchainexplorer.models.api.validator

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class Validator(
    publicKey: String,
    adnlAddr: String,
    weight: BigDecimal,
    cumWeight: BigDecimal
)

object Validator {
  def apply(s: TonApi.LiteServerValidator): Validator =
    Validator(
      publicKey = HexHelper.convertBytesToHex(s.pubkey),
      adnlAddr = HexHelper.convertBytesToHex(s.adnlAddr),
      weight = s.weight,
      cumWeight = s.cumWeight
    )
}
