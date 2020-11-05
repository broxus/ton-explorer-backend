package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class BlockExtra(
    accounts: Seq[BlockExtraAccount],
    randSeed: String,
    createdBy: String,
    custom: Option[McBlockExtra]
)

object BlockExtra {
  def apply(s: TonApi.LiteServerBlockExtra): BlockExtra =
    BlockExtra(
      accounts = s.accounts.map(BlockExtraAccount(_)).toSeq,
      randSeed = HexHelper.convertBytesToHex(s.randSeed),
      createdBy = HexHelper.convertBytesToHex(s.createdBy),
      custom = Option(s.custom).map(McBlockExtra(_))
    )
}
