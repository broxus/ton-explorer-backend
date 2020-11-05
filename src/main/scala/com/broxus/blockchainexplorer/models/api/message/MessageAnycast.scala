package com.broxus.blockchainexplorer.models.api.message

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class MessageAnycast(depth: Int, rewritePfx: String)

object MessageAnycast {
  def apply(a: TonApi.LiteServerMessageAnycast): MessageAnycast =
    MessageAnycast(
      depth = a.depth,
      rewritePfx = HexHelper.convertBytesToHex(a.rewritePfx)
    )
}
