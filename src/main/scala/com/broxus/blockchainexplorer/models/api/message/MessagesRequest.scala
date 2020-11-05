package com.broxus.blockchainexplorer.models.api.message

import com.broxus.blockchainexplorer.models.api.account.AccountId

case class MessagesRequest(
    limit: Int,
    offset: Long,
    fromTs: Option[Int],
    toTs: Option[Int],
    account: Option[AccountId],
    excludeAccounts: Option[Seq[AccountId]]
)
