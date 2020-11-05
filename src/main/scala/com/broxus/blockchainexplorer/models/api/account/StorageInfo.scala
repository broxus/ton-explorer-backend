package com.broxus.blockchainexplorer.models.api.account

import java.math.BigInteger

import com.broxus.blockchainexplorer.models.api.common.StorageUsed
import com.broxus.ton.TonApi

case class StorageInfo(
    storageUsed: StorageUsed,
    lastPaid: Int,
    duePayment: Option[String]
)

object StorageInfo {
  def apply(s: TonApi.LiteServerStorageInfo): StorageInfo =
    StorageInfo(
      storageUsed = StorageUsed(s.storageUsed),
      lastPaid = s.lastPaid,
      duePayment = Option.when(s.hasDuePayment)(new BigInteger(s.duePayment).toString)
    )
}
