package com.broxus.blockchainexplorer.models.api.validator

import com.broxus.blockchainexplorer.models.api.config.{
  BlockchainConfig,
  ConfigValidatorsQuantityLimits,
  ConfigValidatorsStakeLimits,
  ConfigValidatorsTimings
}

case class ValidatorsState(
    quantityLimits: Option[ConfigValidatorsQuantityLimits],
    stakeLimits: Option[ConfigValidatorsStakeLimits],
    timings: Option[ConfigValidatorsTimings],
    previous: Option[ValidatorSet],
    current: Option[ValidatorSet],
    next: Option[ValidatorSet],
    previousStakes: Option[Seq[StakeTransactionListItem]],
    currentStakes: Option[Seq[StakeTransactionListItem]],
    nextStakes: Option[Seq[StakeTransactionListItem]]
)

object ValidatorsState {
  def apply(
      s: BlockchainConfig,
      previousStakes: Option[Seq[StakeTransactionListItem]],
      currentStakes: Option[Seq[StakeTransactionListItem]],
      nextStakes: Option[Seq[StakeTransactionListItem]]
  ): ValidatorsState =
    new ValidatorsState(
      quantityLimits = s.validatorsQuantityLimits,
      stakeLimits = s.validatorsStakeLimits,
      timings = s.validatorsTimings,
      previous = s.prevVset.orElse(s.prevTempVset),
      previousStakes = previousStakes,
      current = s.currVset.orElse(s.currTempVset),
      currentStakes = currentStakes,
      next = s.nextVset.orElse(s.nextTempVset),
      nextStakes = nextStakes
    )
}
