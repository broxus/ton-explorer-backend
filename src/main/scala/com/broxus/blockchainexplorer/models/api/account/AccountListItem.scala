package com.broxus.blockchainexplorer.models.api.account

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.models.DBAccount

case class AccountListItem(workchain: Int, address: String, balance: BigDecimal, created: Int, updated: Int)

object AccountListItem {
  def apply(dbItem: DBAccount): AccountListItem =
    AccountListItem(
      workchain = dbItem.workchain,
      address = HexHelper.convertBytesToHex(dbItem.address),
      balance = dbItem.balance,
      created = dbItem.created,
      updated = dbItem.updated
    )
}
