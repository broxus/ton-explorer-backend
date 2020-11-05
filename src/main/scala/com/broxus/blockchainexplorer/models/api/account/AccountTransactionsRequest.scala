package com.broxus.blockchainexplorer.models.api.account

import com.broxus.blockchainexplorer.models.api.transactions.InternalTransactionId

case class AccountTransactionsRequest(account: String, count: Int, from: Option[InternalTransactionId])
