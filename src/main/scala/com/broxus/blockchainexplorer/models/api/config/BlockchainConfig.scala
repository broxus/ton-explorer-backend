package com.broxus.blockchainexplorer.models.api.config

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.models.api.common.GlobalVersion
import com.broxus.blockchainexplorer.models.api.validator.ValidatorSet
import com.broxus.ton.TonApi

case class BlockchainConfig(
    configAddr: Option[String],
    electorAddr: Option[String],
    minterAddr: Option[String],
    feeCollectorAddr: Option[String],
    dnsRootAddr: Option[String],
    mintPrice: Option[ConfigMintPrice],
    toMint: Option[Map[Int, String]],
    globalVersion: Option[GlobalVersion],
    mandatoryParams: Option[Seq[Int]],
    criticalParams: Option[Seq[Int]],
    configVotingSetup: Option[ConfigVotingSetup],
    workchains: Option[Seq[ConfigWorkchainInfo]],
    complaintPricing: Option[ConfigComplaintPricing],
    blockCreateFees: Option[ConfigBlockCreateFees],
    validatorsTimings: Option[ConfigValidatorsTimings],
    validatorsQuantityLimits: Option[ConfigValidatorsQuantityLimits],
    validatorsStakeLimits: Option[ConfigValidatorsStakeLimits],
    storagePrices: Option[Seq[ConfigStoragePrice]],
    masterchainGasPrices: Option[ConfigGasLimitsPrices],
    basechainGasPrices: Option[ConfigGasLimitsPrices],
    masterchainBlockLimits: Option[ConfigBlockLimits],
    basechainBlockLimits: Option[ConfigBlockLimits],
    masterchainMsgForwardPrices: Option[ConfigMsgForwardPrices],
    basechainMsgForwardPrices: Option[ConfigMsgForwardPrices],
    catchainConfig: Option[CatchainConfig],
    consensusConfig: Option[ConsensusConfig],
    fundamentalSmcAddresses: Option[Seq[String]],
    prevVset: Option[ValidatorSet],
    prevTempVset: Option[ValidatorSet],
    currVset: Option[ValidatorSet],
    currTempVset: Option[ValidatorSet],
    nextVset: Option[ValidatorSet],
    nextTempVset: Option[ValidatorSet]
)

object BlockchainConfig {
  def apply(s: TonApi.LiteServerConfigInfo): BlockchainConfig =
    BlockchainConfig(
      configAddr = Option(s.configAddr).map(a => HexHelper.convertBytesToHex(a.id)),
      electorAddr = Option(s.electorAddr).map(a => HexHelper.convertBytesToHex(a.id)),
      minterAddr = Option(s.minterAddr).map(a => HexHelper.convertBytesToHex(a.id)),
      feeCollectorAddr = Option(s.feeCollectorAddr).map(a => HexHelper.convertBytesToHex(a.id)),
      dnsRootAddr = Option(s.dnsRootAddr).map(a => HexHelper.convertBytesToHex(a.id)),
      mintPrice = Option(s.mintPrice).map(ConfigMintPrice(_)),
      toMint = Option(s.toMint).map(ConfigToMing(_)),
      globalVersion = Option(s.globalVersion).map(GlobalVersion(_)),
      mandatoryParams = Option(s.mandatoryParams).map(item => item.ids.toSeq),
      criticalParams = Option(s.criticalParams).map(item => item.ids.toSeq),
      configVotingSetup = Option(s.configVotingSetup).map(ConfigVotingSetup(_)),
      workchains = Option(s.workchains).map(item => item.workchains.map(ConfigWorkchainInfo(_)).toSeq),
      complaintPricing = Option(s.complaintPricing).map(ConfigComplaintPricing(_)),
      blockCreateFees = Option(s.blockCreateFees).map(ConfigBlockCreateFees(_)),
      validatorsTimings = Option(s.validatorsTimings).map(ConfigValidatorsTimings(_)),
      validatorsQuantityLimits = Option(s.validatorsQuantityLimits).map(ConfigValidatorsQuantityLimits(_)),
      validatorsStakeLimits = Option(s.validatorsStakeLimits).map(ConfigValidatorsStakeLimits(_)),
      storagePrices = Option(s.storagePrices).map(items => items.prices.map(ConfigStoragePrice(_)).toSeq),
      masterchainGasPrices = Option(s.masterchainGasPrices).map(ConfigGasLimitsPrices(_)),
      basechainGasPrices = Option(s.basechainGasPrices).map(ConfigGasLimitsPrices(_)),
      masterchainBlockLimits = Option(s.masterchainBlockLimits).map(ConfigBlockLimits(_)),
      basechainBlockLimits = Option(s.basechainBlockLimits).map(ConfigBlockLimits(_)),
      masterchainMsgForwardPrices = Option(s.masterchainMsgForwardPrices).map(ConfigMsgForwardPrices(_)),
      basechainMsgForwardPrices = Option(s.basechainMsgForwardPrices).map(ConfigMsgForwardPrices(_)),
      catchainConfig = Option(s.catchainConfig).map(CatchainConfig(_)),
      consensusConfig = Option(s.consensusConfig).map(ConsensusConfig(_)),
      fundamentalSmcAddresses =
        Option(s.fundamentalSmcAddresses).map(_.addresses.map(item => HexHelper.convertBytesToHex(item.id)).toSeq),
      prevVset = Option(s.prevVset).map(ValidatorSet(_)),
      prevTempVset = Option(s.prevTempVset).map(ValidatorSet(_)),
      currVset = Option(s.currVset).map(ValidatorSet(_)),
      currTempVset = Option(s.curTempVset).map(ValidatorSet(_)),
      nextVset = Option(s.nextVset).map(ValidatorSet(_)),
      nextTempVset = Option(s.nextTempVset).map(ValidatorSet(_))
    )
}
