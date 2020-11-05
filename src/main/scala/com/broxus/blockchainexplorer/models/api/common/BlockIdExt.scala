package com.broxus.blockchainexplorer.models.api.common

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class BlockIdExt(
    workchain: Int,
    shard: String,
    seqno: Int,
    rootHash: String,
    fileHash: String
)

object BlockIdExt {
  def apply(s: TonApi.TonBlockIdExt): BlockIdExt =
    BlockIdExt(
      workchain = s.workchain,
      shard = s.shard.toHexString,
      seqno = s.seqno,
      rootHash = HexHelper.convertBytesToHex(s.rootHash),
      fileHash = HexHelper.convertBytesToHex(s.fileHash)
    )
}
