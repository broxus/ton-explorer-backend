package com.broxus.blockchainexplorer.models.api.transactions

import com.broxus.blockchainexplorer.repository.models.TransactionType
import com.broxus.ton.TonApi

case class TransactionDescription(
    kind: String,
    storagePhase: Option[TransactionStoragePhase] = None,
    creditPhase: Option[TransactionCreditPhase] = None,
    computePhase: Option[TransactionComputePhase] = None,
    action: Option[TransactionActionPhase] = None,
    bounce: Option[TransactionBouncePhase] = None,
    creditFirst: Option[Boolean] = None,
    isTock: Option[Boolean] = None,
    installed: Option[Boolean] = None,
    aborted: Option[Boolean] = None,
    destroyed: Option[Boolean] = None,
    additionalInfo: Option[TransactionAdditionalInfo] = None
)

object TransactionDescription {
  def apply: TonApi.LiteServerTransactionDescr => TransactionDescription = {
    case s: TonApi.LiteServerTransactionDescrOrdinary     =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.Ordinary),
        creditFirst = Some(s.creditFirst),
        storagePhase = Option(s.storagePh).map(TransactionStoragePhase(_)),
        creditPhase = Option(s.creditPh).map(TransactionCreditPhase(_)),
        computePhase = Option(s.computePh).map(TransactionComputePhase(_)),
        action = Option(s.action).map(TransactionActionPhase(_)),
        bounce = Option(s.bounce).map(TransactionBouncePhase(_)),
        aborted = Some(s.aborted),
        destroyed = Some(s.destroyed),
        additionalInfo = Option(s.additional).map(TransactionAdditionalInfo(_))
      )
    case s: TonApi.LiteServerTransactionDescrStorage      =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.Storage),
        storagePhase = Option(s.storagePh).map(TransactionStoragePhase(_))
      )
    case s: TonApi.LiteServerTransactionDescrTickTock     =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.TickTok),
        isTock = Some(s.isTock),
        storagePhase = Option(s.storagePh).map(TransactionStoragePhase(_)),
        computePhase = Option(s.computePh).map(TransactionComputePhase(_)),
        action = Option(s.action).map(TransactionActionPhase(_)),
        aborted = Some(s.aborted),
        destroyed = Some(s.destroyed)
      )
    case s: TonApi.LiteServerTransactionDescrSplitPrepare =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.SplitPrepare),
        storagePhase = Option(s.storagePh).map(TransactionStoragePhase(_)),
        computePhase = Option(s.computePh).map(TransactionComputePhase(_)),
        action = Option(s.action).map(TransactionActionPhase(_)),
        aborted = Some(s.aborted),
        destroyed = Some(s.destroyed)
      )
    case s: TonApi.LiteServerTransactionDescrSplitInstall =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.SplitInstall),
        installed = Some(s.installed)
      )
    case s: TonApi.LiteServerTransactionDescrMergePrepare =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.MergePrepare),
        storagePhase = Option(s.storagePh).map(TransactionStoragePhase(_)),
        aborted = Some(s.aborted)
      )
    case s: TonApi.LiteServerTransactionDescrMergeInstall =>
      TransactionDescription(
        kind = TransactionType.toEnum(TransactionType.MergeInstall),
        storagePhase = Option(s.storagePh).map(TransactionStoragePhase(_)),
        creditPhase = Option(s.creditPh).map(TransactionCreditPhase(_)),
        computePhase = Option(s.computePh).map(TransactionComputePhase(_)),
        action = Option(s.action).map(TransactionActionPhase(_)),
        aborted = Some(s.aborted),
        destroyed = Some(s.destroyed)
      )
  }
}
