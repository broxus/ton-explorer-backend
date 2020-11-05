package com.broxus.blockchainexplorer.models.api.message

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.models.{ DBMessage, MessageType }

case class MessageListItem(
    bodyHash: String,
    out: Boolean,
    n: Int,
    transactionWorkchain: Int,
    transactionAccountId: String,
    transactionHash: String,
    transactionLt: BigDecimal,
    messageType: String,
    transactionTime: Int,
    value: Option[BigDecimal],
    ihrFee: Option[BigDecimal],
    fwdFee: Option[BigDecimal],
    importFee: Option[BigDecimal],
    srcWorkchain: Option[Int],
    srcAddress: Option[String],
    dstWorkchain: Option[Int],
    dstAddress: Option[String],
    bounce: Option[Boolean],
    bounced: Option[Boolean],
    createdLt: Option[BigDecimal],
    createdAt: Option[Int]
)

object MessageListItem {
  def apply(dbItem: DBMessage): MessageListItem =
    MessageListItem(
      bodyHash = HexHelper.convertBytesToHex(dbItem.bodyHash),
      out = dbItem.out,
      n = dbItem.n,
      transactionWorkchain = dbItem.transactionWorkchain,
      transactionAccountId = HexHelper.convertBytesToHex(dbItem.transactionAccountId),
      transactionHash = HexHelper.convertBytesToHex(dbItem.transactionHash),
      transactionLt = dbItem.transactionLt,
      messageType = MessageType.toEnum(dbItem.messageType),
      transactionTime = dbItem.transactionTime,
      value = dbItem.value,
      ihrFee = dbItem.ihrFee,
      fwdFee = dbItem.fwdFee,
      importFee = dbItem.importFee,
      srcWorkchain = dbItem.srcWorkchain,
      srcAddress = dbItem.srcAddress.map(HexHelper.convertBytesToHex),
      dstWorkchain = dbItem.dstWorkchain,
      dstAddress = dbItem.dstAddress.map(HexHelper.convertBytesToHex),
      bounce = dbItem.bounce,
      bounced = dbItem.bounced,
      createdLt = dbItem.createdLt.map(BigDecimal(_)),
      createdAt = dbItem.createdAt
    )
}
