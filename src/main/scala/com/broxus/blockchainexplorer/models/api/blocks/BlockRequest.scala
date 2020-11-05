package com.broxus.blockchainexplorer.models.api.blocks

case class BlockRequest(
    workchain: Int,
    shard: String,
    seqno: Int
)
