package com.broxus.blockchainexplorer.models.api.validator

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.models.DBStakeTransaction

case class StakeTransactionListItem(
    electorWorkchain: Int,
    electorAccountId: String,
    transactionHash: String,
    transactionLt: Long,
    transactionType: String,
    accountWorkchain: Int,
    accountAddress: String,
    value: BigDecimal,
    time: Int,
    electionTime: Option[Int],
    publicKey: Option[String],
    adnl: Option[String],
    maxFactor: Option[Int]
)

object StakeTransactionListItem {
  def apply(dbItem: DBStakeTransaction): StakeTransactionListItem =
    StakeTransactionListItem(
      electorWorkchain = dbItem.electorWorkchain,
      electorAccountId = HexHelper.convertBytesToHex(dbItem.electorAccountId),
      transactionHash = HexHelper.convertBytesToHex(dbItem.transactionHash),
      transactionLt = dbItem.transactionLt,
      transactionType = dbItem.transactionType.toString,
      accountWorkchain = dbItem.accountWorkchain,
      accountAddress = HexHelper.convertBytesToHex(dbItem.accountAddress),
      value = dbItem.value,
      time = dbItem.time,
      electionTime = dbItem.electionTime,
      publicKey = dbItem.publicKey.map(HexHelper.convertBytesToHex),
      adnl = dbItem.adnl.map(HexHelper.convertBytesToHex),
      maxFactor = dbItem.maxFactor
    )
}
