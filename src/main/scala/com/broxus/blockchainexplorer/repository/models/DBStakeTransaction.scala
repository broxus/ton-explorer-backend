package com.broxus.blockchainexplorer.repository.models

import doobie.util.meta.Meta
import doobie.postgres.implicits._

case class DBStakeTransaction(
    electorWorkchain: Int,
    electorAccountId: Array[Byte],
    transactionHash: Array[Byte],
    transactionLt: Long,
    transactionType: DBStakeTransactionType,
    accountWorkchain: Int,
    accountAddress: Array[Byte],
    value: BigDecimal,
    time: Int,
    electionTime: Option[Int],
    publicKey: Option[Array[Byte]],
    adnl: Option[Array[Byte]],
    maxFactor: Option[Int]
)

sealed trait DBStakeTransactionType

object DBStakeTransactionType {

  case object Send    extends DBStakeTransactionType
  case object Recover extends DBStakeTransactionType

  def toEnum(e: DBStakeTransactionType): String =
    e match {
      case Send    => "send"
      case Recover => "recover"
    }

  def fromEnum(s: String): Option[DBStakeTransactionType] =
    Option(s) collect {
      case "send"    => Send
      case "recover" => Recover
    }

  implicit val DBStakeTransactionTypeMeta: Meta[DBStakeTransactionType] =
    pgEnumStringOpt("stake_transaction_type", DBStakeTransactionType.fromEnum, DBStakeTransactionType.toEnum)
}
