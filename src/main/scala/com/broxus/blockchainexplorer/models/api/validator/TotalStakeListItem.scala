package com.broxus.blockchainexplorer.models.api.validator

case class TotalStakeListItem(
    electionId: Int,
    totalStake: BigDecimal,
    totalWeight: BigDecimal,
    stakes: Seq[ElectionStakeItem]
)
