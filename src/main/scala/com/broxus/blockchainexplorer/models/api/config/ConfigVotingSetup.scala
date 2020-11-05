package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigVotingSetup(
    normalParams: ConfigProposalSetup,
    criticalParams: ConfigProposalSetup
)

object ConfigVotingSetup {
  def apply(s: TonApi.LiteServerConfigVotingSetup): ConfigVotingSetup =
    ConfigVotingSetup(
      normalParams = ConfigProposalSetup(s.normalParams),
      criticalParams = ConfigProposalSetup(s.criticalParams)
    )
}
