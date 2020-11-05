package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConsensusConfig(
    flags: Option[Int] = None,
    newCatchainIds: Option[Boolean] = None,
    roundCandidates: Int,
    nextCandidateDelayMs: Int,
    consensusTimeoutMs: Int,
    fastAttempts: Int,
    attemptDuration: Int,
    catchainMaxDeps: Int,
    maxBlockBytes: Int,
    maxCollatedBytes: Int
)

object ConsensusConfig {
  def apply: TonApi.LiteServerConfigConsensusConfig => ConsensusConfig = {
    case s: TonApi.LiteServerConfigConsensusConfigRegular =>
      ConsensusConfig(
        roundCandidates = s.roundCandidates,
        nextCandidateDelayMs = s.nextCandidateDelayMs,
        consensusTimeoutMs = s.consensusTimeoutMs,
        fastAttempts = s.fastAttempts,
        attemptDuration = s.attemptDuration,
        catchainMaxDeps = s.catchainMaxDeps,
        maxBlockBytes = s.maxBlockBytes,
        maxCollatedBytes = s.maxCollatedBytes
      )
    case s: TonApi.LiteServerConfigConsensusConfigNew     =>
      ConsensusConfig(
        flags = Some(s.flags),
        newCatchainIds = Some(s.newCatchainIds),
        roundCandidates = s.roundCandidates,
        nextCandidateDelayMs = s.nextCandidateDelayMs,
        consensusTimeoutMs = s.consensusTimeoutMs,
        fastAttempts = s.fastAttempts,
        attemptDuration = s.attemptDuration,
        catchainMaxDeps = s.catchainMaxDeps,
        maxBlockBytes = s.maxBlockBytes,
        maxCollatedBytes = s.maxCollatedBytes
      )
  }
}
