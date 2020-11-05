package com.broxus.blockchainexplorer.models.api.transactions

import com.broxus.blockchainexplorer.models.api.account.AccountId

case class TransactionsRequest(
    limit: Int,
    offset: Long,
    fromTs: Option[Int],
    toTs: Option[Int],
    account: Option[AccountId],
    excludeAccounts: Option[Seq[AccountId]],
    transactionTypes: Option[Seq[String]]
)
