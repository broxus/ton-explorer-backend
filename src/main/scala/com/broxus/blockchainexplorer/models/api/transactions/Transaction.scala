package com.broxus.blockchainexplorer.models.api.transactions

import java.math.BigInteger

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.models.api.common.{ BlockIdExt, HashUpdate }
import com.broxus.blockchainexplorer.models.api.message.Message
import com.broxus.ton.TonApi

case class Transaction(
    workchain: Int,
    account: String,
    hash: String,
    lt: BigDecimal,
    prevTransHash: String,
    prevTransLt: BigDecimal,
    now: Int,
    outMessageCount: Int,
    originalStatus: String,
    endStatus: String,
    totalFees: String,
    hashUpdate: HashUpdate,
    messageIn: Option[Message],
    messagesOut: List[Message],
    description: TransactionDescription,
    blockId: Option[BlockIdExt] = None
)

object Transaction {
  private def statusMap: Map[Int, String] =
    Map(
      0 -> "uninit",
      1 -> "frozen",
      2 -> "active",
      3 -> "nonexist"
    )

  def apply(s: TonApi.LiteServerTransaction, blockId: Option[BlockIdExt]): Transaction =
    Transaction(
      workchain = s.workchain,
      account = HexHelper.convertBytesToHex(s.account),
      hash = HexHelper.convertBytesToHex(s.hash),
      lt = s.lt,
      prevTransHash = HexHelper.convertBytesToHex(s.prevTransHash),
      prevTransLt = s.prevTransLt,
      now = s.now,
      outMessageCount = s.outmsgCnt,
      originalStatus = statusMap.getOrElse(s.origStatus, "unknown"),
      endStatus = statusMap.getOrElse(s.endStatus, "unknown"),
      totalFees = new BigInteger(s.totalFees).toString,
      hashUpdate = HashUpdate(s.hashUpdate),
      messageIn = Option(s.inMsg).map(Message(_)),
      messagesOut = s.outMsgs.map(Message(_)).toList,
      description = TransactionDescription(s.desc),
      blockId
    )
}
