package com.broxus.blockchainexplorer.models.api.account

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class SimpleLib(
    isPublic: Boolean,
    data: String
)

object SimpleLib {
  def apply(s: TonApi.LiteServerSimpleLib): SimpleLib =
    SimpleLib(
      isPublic = s.isPublic,
      data = HexHelper.convertBytesToHex(s.root)
    )
}
