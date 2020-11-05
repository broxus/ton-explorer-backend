package com.broxus.blockchainexplorer.models.api.transactions

import java.math.BigInteger

import com.broxus.blockchainexplorer.models.api.common.StorageUsed
import com.broxus.ton.TonApi

case class TransactionBouncePhase(
    kind: String,
    msgSize: Option[StorageUsed] = None,
    reqFwdFees: Option[String] = None,
    msgFees: Option[String] = None,
    fwdFees: Option[String] = None
)

object TransactionBouncePhase {
  def apply: TonApi.LiteServerTransactionBouncePhase => TransactionBouncePhase = {
    case _: TonApi.LiteServerTransactionBouncePhaseNegFunds =>
      TransactionBouncePhase(
        kind = "neg_funds"
      )
    case s: TonApi.LiteServerTransactionBouncePhaseNoFunds  =>
      TransactionBouncePhase(
        kind = "no_funds",
        msgSize = Some(StorageUsed(s.msgSize)),
        reqFwdFees = Some(new BigInteger(s.reqFwdFees).toString)
      )
    case s: TonApi.LiteServerTransactionBouncePhaseOk       =>
      TransactionBouncePhase(
        kind = "ok",
        msgSize = Some(StorageUsed(s.msgSize)),
        msgFees = Some(new BigInteger(s.msgFees).toString),
        fwdFees = Some(new BigInteger(s.fwdFees).toString)
      )
  }
}
