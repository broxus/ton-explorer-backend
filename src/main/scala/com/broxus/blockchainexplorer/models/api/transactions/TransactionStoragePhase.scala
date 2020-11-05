package com.broxus.blockchainexplorer.models.api.transactions

import java.math.BigInteger

import com.broxus.ton.TonApi

case class TransactionStoragePhase(
    storageFeesCollected: String,
    storageFeesDue: Option[String],
    statusChange: String
)

object TransactionStoragePhase {
  private def statusChangeMap: Map[Int, String] =
    Map(
      0 -> "unchanged",
      2 -> "frozen",
      3 -> "deleted"
    )

  def apply(s: TonApi.LiteServerTransactionStoragePhase): TransactionStoragePhase =
    TransactionStoragePhase(
      storageFeesCollected = new BigInteger(s.storageFeesCollected).toString,
      storageFeesDue = Option.when(s.hasStorageFeesDue)(new BigInteger(s.storageFeesDue).toString),
      statusChange = statusChangeMap.getOrElse(s.statusChange, "unknown")
    )
}
