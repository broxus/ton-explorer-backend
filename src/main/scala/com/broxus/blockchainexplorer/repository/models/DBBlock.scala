package com.broxus.blockchainexplorer.repository.models

case class DBBlock(
    workchain: Int,
    shard: Long,
    seqno: Int,
    roothash: Array[Byte],
    filehash: Array[Byte],
    keyBlock: Boolean,
    time: Int,
    transactionsCount: Int
)
