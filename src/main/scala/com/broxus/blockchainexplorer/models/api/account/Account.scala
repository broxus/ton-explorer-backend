package com.broxus.blockchainexplorer.models.api.account

import com.broxus.blockchainexplorer.models.api.common.{ CurrencyCollection }
import com.broxus.blockchainexplorer.models.api.transactions.InternalTransactionId
import com.broxus.blockchainexplorer.{ AccountAddressHelper, HexHelper }
import com.broxus.ton.TonApi

case class Account(
    workchain: Int,
    addressHex: String,
    addressBase64Bounceable: String,
    addressBase64NonBounceable: String,
    storageStat: StorageInfo,
    balance: Option[CurrencyCollection],
    lastTransactionId: InternalTransactionId,
    state: AccountState
)

object Account {
  def apply(accountState: TonApi.LiteServerAccount): Account = {
    val (workchain, addr) = accountState.addr match {
      case a: TonApi.LiteServerMessageAddressIntStd        => (a.workchain, a.address)
      // TODO: handle anycast prefix
      case a: TonApi.LiteServerMessageAddressIntStdAnycast => (a.workchain, a.address)

      // TODO: handle variable address length
      case a: TonApi.LiteServerMessageAddressIntVar        => (a.workchain, a.address)
      // TODO: handle variable address length and anycast prefix
      case a: TonApi.LiteServerMessageAddressIntVarAnycast => (a.workchain, a.address)
    }

    Account(
      workchain = workchain,
      addressHex = HexHelper.convertBytesToHex(addr),
      addressBase64Bounceable = AccountAddressHelper.packAddress(workchain, addr, bounceable = true),
      addressBase64NonBounceable = AccountAddressHelper.packAddress(workchain, addr, bounceable = false),
      storageStat = StorageInfo(accountState.storageStat),
      balance = Option(accountState.balance).map(CurrencyCollection(_)),
      lastTransactionId =
        InternalTransactionId(accountState.lastTransLt, HexHelper.convertBytesToHex(accountState.lastTransHash)),
      state = AccountState(accountState.state)
    )
  }
}
