package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.models.api.common.CurrencyCollection
import com.broxus.ton.TonApi

case class ShardFeesItem(
    fees: CurrencyCollection,
    create: CurrencyCollection
)

object ShardFeesItem {
  def apply(s: TonApi.LiteServerShardFeesItem): ShardFeesItem =
    ShardFeesItem(
      fees = CurrencyCollection(s.fees),
      create = CurrencyCollection(s.create)
    )
}
