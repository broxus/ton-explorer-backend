package com.broxus.blockchainexplorer.models.api.validator

import java.math.BigInteger

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class PastElectionListItem(
    electionId: Int,
    unfreezeAt: Int,
    stakeHeldFor: Int,
    vsetHash: String,
    totalStake: BigDecimal,
    totalBonuses: BigDecimal
)

object PastElectionListItem {
  def apply(s: TonApi.LiteServerPastElectionsItem): PastElectionListItem =
    PastElectionListItem(
      electionId = s.electionId,
      unfreezeAt = s.unfreezeAt,
      stakeHeldFor = s.stakeHeldFor,
      vsetHash = HexHelper.convertBytesToHex(s.vsetHash),
      totalStake = BigDecimal(new BigInteger(s.totalStake)),
      totalBonuses = BigDecimal(new BigInteger(s.totalBonuses))
    )
}
