package com.broxus.blockchainexplorer.repository.models

import doobie.postgres.implicits.pgEnumStringOpt
import doobie.util.meta.Meta

case class DBTransaction(
    workchain: Int,
    accountId: Array[Byte],
    hash: Array[Byte],
    lt: Long,
    blockShard: Long,
    blockSeqno: Int,
    transactionType: TransactionType,
    balanceChange: BigDecimal,
    time: Int,
    totalFees: BigDecimal,
    storageFeesCollected: Option[BigDecimal],
    storageFeesDue: Option[BigDecimal],
    dueFeesCollected: Option[BigDecimal],
    gasFees: Option[BigDecimal],
    totalFwdFees: Option[BigDecimal],
    totalActionFees: Option[BigDecimal],
    reqFwdFees: Option[BigDecimal],
    msgFees: Option[BigDecimal],
    fwdFees: Option[BigDecimal],
    aborted: Option[Boolean],
    destroyed: Option[Boolean],
    creditFirst: Option[Boolean],
    isTock: Option[Boolean]
)

sealed trait TransactionType

object TransactionType {

  case object MergeInstall extends TransactionType
  case object MergePrepare extends TransactionType
  case object Ordinary     extends TransactionType
  case object SplitPrepare extends TransactionType
  case object TickTok      extends TransactionType
  case object SplitInstall extends TransactionType
  case object Storage      extends TransactionType
  case object Unknown      extends TransactionType

  def toEnum(e: TransactionType): String =
    e match {
      case MergeInstall => "merge_install"
      case MergePrepare => "merge_prepare"
      case Ordinary     => "ordinary"
      case SplitPrepare => "split_prepare"
      case TickTok      => "tick_tok"
      case SplitInstall => "split_install"
      case Storage      => "storage"
      case Unknown      => "unknown"
    }

  def fromEnum(s: String): Option[TransactionType] =
    Option(s) collect {
      case "merge_install" => MergeInstall
      case "merge_prepare" => MergePrepare
      case "ordinary"      => Ordinary
      case "split_prepare" => SplitPrepare
      case "tick_tok"      => TickTok
      case "split_install" => SplitInstall
      case "storage"       => Storage
      case "unknown"       => Unknown
    }

  implicit val TransactionTypeMeta: Meta[TransactionType] =
    pgEnumStringOpt("transaction_type", TransactionType.fromEnum, TransactionType.toEnum)
}
