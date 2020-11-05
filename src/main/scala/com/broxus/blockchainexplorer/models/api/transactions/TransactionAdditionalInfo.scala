package com.broxus.blockchainexplorer.models.api.transactions

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class TransactionAdditionalInfo(
    kind: String,
    success: Boolean,
    status: Option[Int] = None,
    pubkey: Option[String] = None,
    electionTime: Option[Int] = None,
    maxFactor: Option[Int] = None,
    adnlAddr: Option[String] = None
)

object TransactionAdditionalInfo {
  def apply: TonApi.LiteServerTransactionAdditionalInfo => TransactionAdditionalInfo = {
    case s: TonApi.LiteServerTransactionAdditionalInfoStakeSend    =>
      TransactionAdditionalInfo(
        kind = "stake_send",
        success = s.status == -1,
        status = Some(s.status),
        pubkey = Some(HexHelper.convertBytesToHex(s.pubkey)),
        electionTime = Some(s.electTime),
        maxFactor = Some(s.maxFactor),
        adnlAddr = Some(HexHelper.convertBytesToHex(s.adnlAddr))
      )
    case s: TonApi.LiteServerTransactionAdditionalInfoStakeRecover =>
      TransactionAdditionalInfo(
        kind = "stake_recover",
        success = s.success
      )
  }
}
