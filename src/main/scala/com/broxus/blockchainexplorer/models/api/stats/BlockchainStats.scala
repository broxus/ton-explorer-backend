package com.broxus.blockchainexplorer.models.api.stats

case class BlockchainStats(
    totalSupply: BigDecimal,
    volume24: BigDecimal,
//    topBlocks: Seq[TopBlock],
    maxSeqno: Int,
    counts: Map[String, BlockchainStatsCounts]
)

case class TopBlock(workchain: Int, maxSeqno: Int, blocksH1Count: Long)

case class BlockchainStatsCounts(
    time: Int,
    accountsCreated: Long,
    accountsActive: Long,
    transactions: Long,
    messages: Long
)
