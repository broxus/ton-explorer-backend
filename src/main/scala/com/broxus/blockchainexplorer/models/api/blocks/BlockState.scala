package com.broxus.blockchainexplorer.models.api.blocks

import com.broxus.blockchainexplorer.models.api.account.Account
import com.broxus.blockchainexplorer.models.api.common.CurrencyCollection
import com.broxus.ton.TonApi

case class BlockState(
    utime: Int,
    lt: BigDecimal,
    totalBalance: Option[CurrencyCollection],
    totalValidatorFees: Option[CurrencyCollection],
    globalBalance: Option[CurrencyCollection],
    accounts: Seq[Account]
)

object BlockState {
  def apply(s: TonApi.LiteServerBlockState): BlockState =
    BlockState(
      utime = s.utime,
      lt = s.lt,
      totalBalance = Option(s.totalBalance).map(CurrencyCollection(_)),
      totalValidatorFees = Option(s.totalValidatorFees).map(CurrencyCollection(_)),
      globalBalance = Option(s.globalBalance).map(CurrencyCollection(_)),
      accounts = s.accounts.map(Account(_)).toSeq
    )
}
