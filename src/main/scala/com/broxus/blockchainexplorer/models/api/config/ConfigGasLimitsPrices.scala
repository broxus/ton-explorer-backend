package com.broxus.blockchainexplorer.models.api.config

import com.broxus.ton.TonApi

case class ConfigGasLimitsPrices(
    gasPrice: Option[BigDecimal] = None,
    gasLimit: Option[BigDecimal] = None,
    specialGasLimit: Option[BigDecimal] = None,
    gasCredit: Option[BigDecimal] = None,
    blockGasLimit: Option[BigDecimal] = None,
    freezeDueLimit: Option[BigDecimal] = None,
    deleteDueLimit: Option[BigDecimal] = None,
    flatGasLimit: Option[BigDecimal] = None,
    flatGasPrice: Option[BigDecimal] = None
)

object ConfigGasLimitsPrices {
  def apply: TonApi.LiteServerConfigGasLimitsPrices => ConfigGasLimitsPrices = {
    case s: TonApi.LiteServerConfigGasPrices    =>
      ConfigGasLimitsPrices(
        gasPrice = Some(s.gasPrice),
        gasLimit = Some(s.gasLimit),
        gasCredit = Some(s.gasCredit),
        blockGasLimit = Some(s.blockGasLimit),
        freezeDueLimit = Some(s.freezeDueLimit),
        deleteDueLimit = Some(s.deleteDueLimit)
      )
    case s: TonApi.LiteServerConfigGasPricesExt =>
      ConfigGasLimitsPrices(
        gasPrice = Some(s.gasPrice),
        gasLimit = Some(s.gasLimit),
        specialGasLimit = Some(s.specialGasLimit),
        gasCredit = Some(s.gasCredit),
        blockGasLimit = Some(s.blockGasLimit),
        freezeDueLimit = Some(s.freezeDueLimit),
        deleteDueLimit = Some(s.deleteDueLimit)
      )
    case s: TonApi.LiteServerConfigGasFlatPfx   =>
      ConfigGasLimitsPrices(
        flatGasLimit = Some(s.flatGasLimit),
        flatGasPrice = Some(s.flatGasPrice)
      )
  }
}
