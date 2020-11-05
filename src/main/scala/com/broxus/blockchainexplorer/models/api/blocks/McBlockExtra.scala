package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.models.api.config.BlockchainConfig
import com.broxus.ton.TonApi

case class McBlockExtra(
    isKeyBlock: Boolean,
    shardHashes: AllShardsInfo,
    config: Option[BlockchainConfig]
)

object McBlockExtra {
  def apply(s: TonApi.LiteServerMcBlockExtra): McBlockExtra =
    McBlockExtra(
      isKeyBlock = s.keyBlock,
      shardHashes = AllShardsInfo(s.shardHashes),
      config = Option(s.config).map(BlockchainConfig(_))
    )
}
