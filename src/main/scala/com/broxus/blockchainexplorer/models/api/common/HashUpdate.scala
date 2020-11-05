package com.broxus.blockchainexplorer.models.api.common

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class HashUpdate(
    oldHash: String,
    newHash: String
)

object HashUpdate {
  def apply(s: TonApi.LiteServerHashUpdate): HashUpdate =
    HashUpdate(
      oldHash = HexHelper.convertBytesToHex(s.oldHash),
      newHash = HexHelper.convertBytesToHex(s.newHash)
    )
}
