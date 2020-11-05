package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.models.api.common.{ BlockId, BlockIdExt }
import com.broxus.blockchainexplorer.models.api.transactions.Transaction
import com.broxus.ton.TonApi

case class Block(
    id: BlockIdExt,
    masterchainId: BlockIdExt,
    globalId: Int,
    info: BlockInfo,
    valueFlow: ValueFlow,
    blockExtra: BlockExtra,
    previous: Seq[BlockIdExt],
    next: Seq[BlockId],
    transactions: Seq[Transaction]
)

object Block {
  def apply(s: TonApi.LiteServerBlock): Block =
    Block(
      id = BlockIdExt(s.id),
      masterchainId = BlockIdExt(s.masterchainId),
      globalId = s.globalId,
      info = BlockInfo(s.info),
      valueFlow = ValueFlow(s.valueFlow),
      blockExtra = BlockExtra(s.blockExtra),
      previous = s.previous.map(BlockIdExt(_)).toSeq,
      next = s.next.map(BlockId(_)).toSeq,
      transactions = s.transactions.map(Transaction(_, None)).toSeq
    )
}
