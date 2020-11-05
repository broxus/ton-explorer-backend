package com.broxus.blockchainexplorer.models.api.message

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class Message(
    hash: String,
    info: MessageInfo,
    init: Option[String],
    body: Option[String]
)

object Message {
  def apply(s: TonApi.LiteServerMessage): Message =
    Message(
      hash = HexHelper.convertBytesToHex(s.hash),
      info = MessageInfo(s.info),
      init = Option(s.init).map(HexHelper.convertBytesToHex),
      body = Option(s.body).map(HexHelper.convertBytesToHex)
    )
}
