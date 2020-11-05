package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigProposalSetup(
    minTotRounds: Int,
    maxTotRounds: Int,
    minWinds: Int,
    maxLosses: Int,
    minStoreSeq: Int,
    maxStoreSeq: Int,
    bitPrice: Int,
    cellPrice: Int
)

object ConfigProposalSetup {
  def apply(s: TonApi.LiteServerConfigProposalSetup): ConfigProposalSetup =
    ConfigProposalSetup(
      minTotRounds = s.minTotRounds,
      maxTotRounds = s.maxTotRounds,
      minWinds = s.minWins,
      maxLosses = s.maxLosses,
      minStoreSeq = s.minStoreSec,
      maxStoreSeq = s.maxStoreSec,
      bitPrice = s.bitPrice,
      cellPrice = s.cellPrice
    )
}
