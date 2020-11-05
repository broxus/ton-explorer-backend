package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.models.DBBlock

case class BlockListItem(
    workchain: Int,
    shard: String,
    seqno: Int,
    roothash: String,
    filehash: String,
    keyBlock: Boolean,
    time: Int,
    transactionsCount: Int
)

object BlockListItem {

  def apply(dbItem: DBBlock): BlockListItem =
    BlockListItem(
      workchain = dbItem.workchain,
      shard = dbItem.shard.toHexString,
      seqno = dbItem.seqno,
      roothash = HexHelper.convertBytesToHex(dbItem.roothash),
      filehash = HexHelper.convertBytesToHex(dbItem.filehash),
      keyBlock = dbItem.keyBlock,
      time = dbItem.time,
      transactionsCount = dbItem.transactionsCount
    )
}
