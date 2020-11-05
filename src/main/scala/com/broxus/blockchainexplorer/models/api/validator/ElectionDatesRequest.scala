package com.broxus.blockchainexplorer.models.api.validator

case class ElectionDatesRequest(
    fromTs: Option[Int],
    toTs: Option[Int]
)
