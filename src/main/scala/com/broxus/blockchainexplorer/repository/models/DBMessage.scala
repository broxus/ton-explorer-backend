package com.broxus.blockchainexplorer.repository.models

import doobie.postgres.implicits.pgEnumStringOpt
import doobie.util.meta.Meta

case class DBMessage(
    bodyHash: Array[Byte],
    out: Boolean,
    n: Int,
    transactionWorkchain: Int,
    transactionAccountId: Array[Byte],
    transactionHash: Array[Byte],
    transactionLt: Long,
    messageType: MessageType,
    transactionTime: Int,
    value: Option[BigDecimal],
    ihrFee: Option[BigDecimal],
    fwdFee: Option[BigDecimal],
    importFee: Option[BigDecimal],
    srcWorkchain: Option[Int],
    srcAddress: Option[Array[Byte]],
    dstWorkchain: Option[Int],
    dstAddress: Option[Array[Byte]],
    bounce: Option[Boolean],
    bounced: Option[Boolean],
    createdLt: Option[Long],
    createdAt: Option[Int]
)

sealed trait MessageType

object MessageType {

  case object Internal    extends MessageType
  case object ExternalIn  extends MessageType
  case object ExternalOut extends MessageType

  def toEnum(e: MessageType): String =
    e match {
      case Internal    => "internal"
      case ExternalIn  => "external_in"
      case ExternalOut => "external_out"
    }

  def fromEnum(s: String): Option[MessageType] =
    Option(s) collect {
      case "internal"     => Internal
      case "external_in"  => ExternalIn
      case "external_out" => ExternalOut
    }

  implicit val MessageTypeMeta: Meta[MessageType] =
    pgEnumStringOpt("message_type", MessageType.fromEnum, MessageType.toEnum)
}
