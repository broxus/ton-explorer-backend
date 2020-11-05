package com.broxus.blockchainexplorer.models.api.validator

import com.broxus.blockchainexplorer.models.api.account.AccountId

case class StakeTransactionListRequest(
    limit: Int,
    offset: Long,
    fromTs: Option[Int],
    toTs: Option[Int],
    account: Option[AccountId],
    electionTime: Option[Int]
)
