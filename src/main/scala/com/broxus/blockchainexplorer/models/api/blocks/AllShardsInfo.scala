package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.ton.TonApi

case class AllShardsInfo(
    minShardGenUtime: Int,
    maxShardGenUtime: Int,
    shards: Seq[ShardHash]
)

object AllShardsInfo {
  def apply(s: TonApi.LiteServerAllShardsInfo): AllShardsInfo =
    AllShardsInfo(
      minShardGenUtime = s.minShardGenUtime,
      maxShardGenUtime = s.maxShardGenUtime,
      shards = s.shards.map(ShardHash(_)).toSeq
    )
}
