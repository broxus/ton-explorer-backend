package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.models.api.common.CurrencyCollection
import com.broxus.ton.TonApi

case class ValueFlow(
    fromPrevBlk: CurrencyCollection,
    toNextBlk: CurrencyCollection,
    imported: CurrencyCollection,
    exported: CurrencyCollection,
    feesCollected: CurrencyCollection,
    feesImported: CurrencyCollection,
    recovered: CurrencyCollection,
    created: CurrencyCollection,
    minted: CurrencyCollection
)

object ValueFlow {
  def apply(s: TonApi.LiteServerValueFlow): ValueFlow =
    ValueFlow(
      fromPrevBlk = CurrencyCollection(s.fromPrevBlk),
      toNextBlk = CurrencyCollection(s.toNextBlk),
      imported = CurrencyCollection(s.imported),
      exported = CurrencyCollection(s.exported),
      feesCollected = CurrencyCollection(s.feesCollected),
      feesImported = CurrencyCollection(s.feesImported),
      recovered = CurrencyCollection(s.recovered),
      created = CurrencyCollection(s.created),
      minted = CurrencyCollection(s.minted)
    )
}
