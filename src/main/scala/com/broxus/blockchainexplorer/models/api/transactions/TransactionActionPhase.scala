package com.broxus.blockchainexplorer.models.api.transactions

import java.math.BigInteger

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.models.api.common.StorageUsed
import com.broxus.ton.TonApi

case class TransactionActionPhase(
    success: Boolean,
    valid: Boolean,
    noFunds: Boolean,
    statusChange: String,
    totalFwdFees: Option[String],
    totalActionFees: Option[String],
    resultCode: Int,
    resultArg: Option[Int],
    totalActions: Int,
    specialActions: Int,
    skippedActions: Int,
    messagesCreated: Int,
    actionListHash: String,
    totalMsgSize: StorageUsed
)

object TransactionActionPhase {
  private def statusChangeMap: Map[Int, String] =
    Map(
      0 -> "unchanged",
      2 -> "frozen",
      3 -> "deleted"
    )

  def apply(s: TonApi.LiteServerTransactionActionPhase): TransactionActionPhase =
    TransactionActionPhase(
      success = s.success,
      valid = s.valid,
      noFunds = s.noFunds,
      statusChange = statusChangeMap.getOrElse(s.statusChange, "unknown"),
      totalFwdFees = Option.when(s.hasTotalFwdFees)(new BigInteger(s.totalFwdFees).toString),
      totalActionFees = Option.when(s.hasTotalActionFees)(new BigInteger(s.totalActionFees).toString),
      resultCode = s.resultCode,
      resultArg = Option.when(s.hasResultArg)(s.resultArg),
      totalActions = s.totActions,
      specialActions = s.specActions,
      skippedActions = s.skippedActions,
      messagesCreated = s.msgsCreated,
      actionListHash = HexHelper.convertBytesToHex(s.actionListHash),
      totalMsgSize = StorageUsed(s.totMsgSize)
    )
}
