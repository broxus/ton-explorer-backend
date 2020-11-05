package com.broxus.blockchainexplorer.models.api.transactions

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.models.{ DBTransaction, TransactionType }

case class TransactionListItem(
    workchain: Int,
    accountId: String,
    hash: String,
    lt: BigDecimal,
    blockShard: String,
    blockSeqno: Int,
    transactionType: String,
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

object TransactionListItem {
  def apply(dbItem: DBTransaction): TransactionListItem =
    TransactionListItem(
      workchain = dbItem.workchain,
      accountId = HexHelper.convertBytesToHex(dbItem.accountId),
      hash = HexHelper.convertBytesToHex(dbItem.hash),
      lt = dbItem.lt,
      blockShard = dbItem.blockShard.toHexString,
      blockSeqno = dbItem.blockSeqno,
      transactionType = TransactionType.toEnum(dbItem.transactionType),
      balanceChange = dbItem.balanceChange,
      time = dbItem.time,
      totalFees = dbItem.totalFees,
      storageFeesCollected = dbItem.storageFeesCollected,
      storageFeesDue = dbItem.storageFeesDue,
      dueFeesCollected = dbItem.dueFeesCollected,
      gasFees = dbItem.gasFees,
      totalFwdFees = dbItem.totalFwdFees,
      totalActionFees = dbItem.totalActionFees,
      reqFwdFees = dbItem.reqFwdFees,
      msgFees = dbItem.msgFees,
      fwdFees = dbItem.fwdFees,
      aborted = dbItem.aborted,
      destroyed = dbItem.destroyed,
      creditFirst = dbItem.creditFirst,
      isTock = dbItem.isTock
    )
}
