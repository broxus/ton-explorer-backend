package com.broxus.blockchainexplorer.models.api.message

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class MessageAddress(anycast: Option[MessageAnycast], workchain: Int, addressLength: Option[Int], address: String)

object MessageAddress {
  def apply: TonApi.LiteServerMessageAddressExt => MessageAddress  = _ => MessageAddress(None, 0, None, "")
  def apply(s: TonApi.LiteServerMessageAddressInt): MessageAddress =
    s match {
      case s: TonApi.LiteServerMessageAddressIntStd        =>
        MessageAddress(
          anycast = None,
          workchain = s.workchain,
          addressLength = None,
          address = HexHelper.convertBytesToHex(s.address)
        )
      case s: TonApi.LiteServerMessageAddressIntStdAnycast =>
        MessageAddress(
          anycast = Some(MessageAnycast(s.anycast)),
          workchain = s.workchain,
          addressLength = None,
          address = HexHelper.convertBytesToHex(s.address)
        )
      case s: TonApi.LiteServerMessageAddressIntVar        =>
        MessageAddress(
          anycast = None,
          workchain = s.workchain,
          addressLength = Some(s.addrLen),
          address = HexHelper.convertBytesToHex(s.address)
        )
      case s: TonApi.LiteServerMessageAddressIntVarAnycast =>
        MessageAddress(
          anycast = Some(MessageAnycast(s.anycast)),
          workchain = s.workchain,
          addressLength = Some(s.addrLen),
          address = HexHelper.convertBytesToHex(s.address)
        )
    }
}
