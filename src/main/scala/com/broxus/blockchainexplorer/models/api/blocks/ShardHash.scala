package com.broxus.blockchainexplorer.models.api.blocks

import java.math.BigInteger

import com.broxus.blockchainexplorer.models.api.common.BlockIdExt
import com.broxus.ton.TonApi

case class ShardHash(
    workchain: Int,
    id: String,
    topBlockId: BlockIdExt,
    startLt: BigDecimal,
    endLt: BigDecimal,
    beforeSplit: Boolean,
    beforeMerge: Boolean,
    wantSplit: Boolean,
    wantMerge: Boolean,
    nextCatchainSeqno: Int,
    nextValidatorShard: String,
    minRefMcSeqno: Int,
    genUtime: Int,
    feesCollected: String,
    fundsCollected: String
)

object ShardHash {
  def apply(s: TonApi.LiteServerShardHash): ShardHash =
    ShardHash(
      workchain = s.workchain,
      id = s.id.toHexString,
      topBlockId = BlockIdExt(s.topBlockId),
      startLt = s.startLt,
      endLt = s.endLt,
      beforeSplit = s.beforeSplit,
      beforeMerge = s.beforeMerge,
      wantSplit = s.wantSplit,
      wantMerge = s.wantMerge,
      nextCatchainSeqno = s.nextCatchainSeqno,
      nextValidatorShard = s.nextValidatorShard.toHexString,
      minRefMcSeqno = s.minRefMcSeqno,
      genUtime = s.genUtime,
      feesCollected = new BigInteger(s.feesCollected).toString,
      fundsCollected = new BigInteger(s.fundsCollected).toString
    )
}
