package com.broxus.blockchainexplorer.models.api.transactions

import java.math.BigInteger

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class TransactionComputePhase(
    kind: String,
    reason: Option[Int] = None,
    success: Option[Boolean] = None,
    msgStateUsed: Option[Boolean] = None,
    accountActivated: Option[Boolean] = None,
    gasFees: Option[String] = None,
    gasUsed: Option[String] = None,
    gasLimit: Option[String] = None,
    gasCredit: Option[String] = None,
    mode: Option[Int] = None,
    exitCode: Option[Int] = None,
    exitArg: Option[Int] = None,
    vmSteps: Option[Int] = None,
    vmInitStateHash: Option[String] = None,
    vmFinalStateHash: Option[String] = None
)

object TransactionComputePhase {
  def apply: TonApi.LiteServerTransactionComputePhase => TransactionComputePhase = {
    case s: TonApi.LiteServerTransactionComputePhaseSkipped =>
      TransactionComputePhase(
        kind = "skipped",
        reason = Some(s.reason)
      )
    case s: TonApi.LiteServerTransactionComputePhaseVm      =>
      TransactionComputePhase(
        kind = "vm",
        success = Some(s.success),
        msgStateUsed = Some(s.msgStateUsed),
        accountActivated = Some(s.accountActivated),
        gasFees = Some(new BigInteger(s.gasFees).toString),
        gasUsed = Some(new BigInteger(s.gasUsed).toString),
        gasLimit = Some(new BigInteger(s.gasLimit).toString),
        gasCredit = Option.when(s.hasGasCredit)(new BigInteger(s.gasCredit).toString),
        mode = Some(s.mode),
        exitCode = Some(s.exitCode),
        exitArg = Option.when(s.hasExitArg)(s.exitArg),
        vmSteps = Some(s.vmSteps),
        vmInitStateHash = Some(HexHelper.convertBytesToHex(s.vmInitStateHash)),
        vmFinalStateHash = Some(HexHelper.convertBytesToHex(s.vmFinalStateHash))
      )
  }
}
