package com.broxus.blockchainexplorer.models.api.common

import com.broxus.ton.TonApi

case class BlockId(
    workchain: Int,
    shard: String,
    seqno: Int
)

object BlockId {
  def apply(s: TonApi.TonBlockId): BlockId =
    BlockId(
      workchain = s.workchain,
      shard = s.shard.toHexString,
      seqno = s.seqno
    )
}
