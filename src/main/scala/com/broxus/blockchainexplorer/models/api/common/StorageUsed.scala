package com.broxus.blockchainexplorer.models.api.common

import java.math.BigInteger

import com.broxus.ton.TonApi

case class StorageUsed(
    cells: String,
    bits: String,
    publicCells: Option[String] = None
)

object StorageUsed {
  def apply(s: TonApi.LiteServerStorageUsedShort): StorageUsed =
    StorageUsed(
      cells = new BigInteger(s.cells).toString,
      bits = new BigInteger(s.bits).toString
    )
  def apply(s: TonApi.LiteServerStorageUsed): StorageUsed      =
    StorageUsed(
      cells = new BigInteger(s.cells).toString,
      bits = new BigInteger(s.bits).toString,
      publicCells = Some(new BigInteger(s.publicCells).toString)
    )
}
