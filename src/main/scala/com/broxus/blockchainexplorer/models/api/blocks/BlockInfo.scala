package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.models.api.common.GlobalVersion
import com.broxus.ton.TonApi

case class BlockInfo(
    version: Int,
    notMaster: Boolean,
    afterMerge: Boolean,
    beforeSplit: Boolean,
    afterSplit: Boolean,
    wantSplit: Boolean,
    wantMerge: Boolean,
    keyBlock: Boolean,
    vertSeqnoIncr: Boolean,
    flags: Int,
    seqno: Int,
    vertSeqno: Int,
    genUtime: Int,
    startLt: BigDecimal,
    endLt: BigDecimal,
    genValidatorListHashShort: Int,
    genCatchainSeqno: Int,
    minRefMcSeqno: Int,
    prevKeyBlockSeqno: Int,
    genSoftware: GlobalVersion,
    masterRef: Option[ExtBlockRef]
)

object BlockInfo {
  def apply(s: TonApi.LiteServerBlockInfo): BlockInfo =
    BlockInfo(
      version = s.version,
      notMaster = s.notMaster,
      afterMerge = s.afterMerge,
      beforeSplit = s.beforeSplit,
      afterSplit = s.afterSplit,
      wantSplit = s.wantSplit,
      wantMerge = s.wantMerge,
      keyBlock = s.keyBlock,
      vertSeqnoIncr = s.vertSeqnoIncr,
      flags = s.flags,
      seqno = s.seqNo,
      vertSeqno = s.vertSeqNo,
      genUtime = s.genUtime,
      startLt = s.startLt,
      endLt = s.endLt,
      genValidatorListHashShort = s.genValidatorListHashShort,
      genCatchainSeqno = s.genCatchainSeqno,
      minRefMcSeqno = s.minRefMcSeqno,
      prevKeyBlockSeqno = s.prevKeyBlockSeqno,
      genSoftware = GlobalVersion(s.genSoftware),
      masterRef = Option(s.masterRef).map(ExtBlockRef(_))
    )
}
