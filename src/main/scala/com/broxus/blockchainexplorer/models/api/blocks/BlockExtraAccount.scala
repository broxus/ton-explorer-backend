package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.models.api.common.HashUpdate
import com.broxus.ton.TonApi

case class BlockExtraAccount(
    addr: String,
    transactionCount: Int,
    stateUpdate: HashUpdate
)

object BlockExtraAccount {
  def apply(s: TonApi.LiteServerBlockExtraAccount): BlockExtraAccount =
    BlockExtraAccount(
      addr = HexHelper.convertBytesToHex(s.addr),
      transactionCount = s.transactionCnt,
      stateUpdate = HashUpdate(s.stateUpdate)
    )
}
