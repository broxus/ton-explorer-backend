package com.broxus.blockchainexplorer.models.api.transactions

import java.math.BigInteger

import com.broxus.blockchainexplorer.models.api.common.CurrencyCollection
import com.broxus.ton.TonApi

case class TransactionCreditPhase(
    dueFeesCollected: Option[String],
    credit: CurrencyCollection
)

object TransactionCreditPhase {
  def apply(s: TonApi.LiteServerTransactionCreditPhase): TransactionCreditPhase =
    TransactionCreditPhase(
      dueFeesCollected = Option.when(s.hasDueFeesCollected)(new BigInteger(s.dueFeesCollected).toString),
      credit = CurrencyCollection(s.credit)
    )
}
