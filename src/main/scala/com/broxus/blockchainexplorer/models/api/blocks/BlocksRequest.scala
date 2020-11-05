package com.broxus.blockchainexplorer.models.api.blocks

case class BlocksRequest(
    limit: Int,
    offset: Long,
    fromTs: Option[Int],
    toTs: Option[Int],
    skipEmpty: Boolean
)
