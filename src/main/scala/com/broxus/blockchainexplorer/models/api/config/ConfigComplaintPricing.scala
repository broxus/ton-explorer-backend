package com.broxus.blockchainexplorer.models.api.config

import java.math.BigInteger

import com.broxus.ton.TonApi

case class ConfigComplaintPricing(
    deposit: String,
    bitPrice: String,
    cellPrice: String
)

object ConfigComplaintPricing {
  def apply(s: TonApi.LiteServerConfigComplaintPricing): ConfigComplaintPricing =
    ConfigComplaintPricing(
      deposit = new BigInteger(s.deposit).toString,
      bitPrice = new BigInteger(s.bitPrice).toString,
      cellPrice = new BigInteger(s.cellPrice).toString
    )
}
