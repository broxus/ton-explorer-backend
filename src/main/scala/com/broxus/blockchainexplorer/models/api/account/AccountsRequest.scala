package com.broxus.blockchainexplorer.models.api.account

case class AccountsRequest(
    limit: Int,
    offset: Long,
    orderColumn: String,
    asc: Boolean,
    fromTs: Option[Int],
    toTs: Option[Int]
)
