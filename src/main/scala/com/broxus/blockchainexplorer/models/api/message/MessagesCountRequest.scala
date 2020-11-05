package com.broxus.blockchainexplorer.models.api.message

import com.broxus.blockchainexplorer.models.api.account.AccountId

case class MessagesCountRequest(
    fromTs: Option[Int],
    toTs: Option[Int],
    out: Option[Boolean],
    account: Option[AccountId],
    excludeAccounts: Option[Seq[AccountId]]
)
