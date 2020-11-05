package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class ExtBlockRef(
    endLt: BigDecimal,
    seqno: Int,
    rootHash: String,
    fileHash: String
)

object ExtBlockRef {
  def apply(s: TonApi.LiteServerExtBlockRef): ExtBlockRef =
    ExtBlockRef(
      endLt = s.endLt,
      seqno = s.seqno,
      rootHash = HexHelper.convertBytesToHex(s.rootHash),
      fileHash = HexHelper.convertBytesToHex(s.fileHash)
    )
}
