package com.broxus.blockchainexplorer.models.api.validator

case class ElectionStakeItem(
    publicKey: String,
    address: String,
    weight: BigDecimal,
    trueStake: BigDecimal,
    unaccountedStake: BigDecimal
)
