package com.broxus.blockchainexplorer.models.api.message

import java.math.BigInteger

import com.broxus.ton.TonApi

case class MessageInfo(
    kind: String,
    src: Option[MessageAddress] = None,
    dest: Option[MessageAddress] = None,
    ihrDisabled: Option[Boolean] = None,
    bounce: Option[Boolean] = None,
    bounced: Option[Boolean] = None,
    value: Option[BigDecimal] = None,
    ihrFee: Option[BigDecimal] = None,
    fwdFee: Option[BigDecimal] = None,
    importFee: Option[BigDecimal] = None,
    createdLt: Option[BigDecimal] = None,
    createdAt: Option[Int] = None
)

object MessageInfo {
  def apply: TonApi.LiteServerMessageInfo => MessageInfo = {
    case s: TonApi.LiteServerMessageInfoInt    =>
      MessageInfo(
        kind = "internal",
        src = Some(MessageAddress(s.src)),
        dest = Some(MessageAddress(s.dest)),
        ihrDisabled = Some(s.ihrDisabled),
        bounce = Some(s.bounce),
        bounced = Some(s.bounced),
        value = Some(BigDecimal(new BigInteger(s.value))),
        ihrFee = Some(BigDecimal(new BigInteger(s.ihrFee))),
        fwdFee = Some(BigDecimal(new BigInteger(s.fwdFee))),
        createdLt = Some(s.createdLt),
        createdAt = Some(s.createdAt)
      )
    case s: TonApi.LiteServerMessageInfoExtIn  =>
      MessageInfo(
        kind = "external_in",
        dest = Some(MessageAddress(s.dest)),
        importFee = Some(BigDecimal(new BigInteger(s.importFee)))
      )
    case s: TonApi.LiteServerMessageInfoExtOut =>
      MessageInfo(
        kind = "external_out",
        src = Some(MessageAddress(s.src)),
        createdLt = Some(s.createdLt),
        createdAt = Some(s.createdAt)
      )
  }
}
