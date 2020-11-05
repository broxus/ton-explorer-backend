package com.broxus.blockchainexplorer.models.api.config

import java.math.BigInteger

import com.broxus.ton.TonApi

case class ConfigBlockCreateFees(
    masterchainBlockFee: String,
    basechainBlockFee: String
)

object ConfigBlockCreateFees {
  def apply(s: TonApi.LiteServerConfigBlockCreateFees): ConfigBlockCreateFees =
    ConfigBlockCreateFees(
      masterchainBlockFee = new BigInteger(s.masterchainBlockFee).toString,
      basechainBlockFee = new BigInteger(s.basechainBlockFee).toString
    )
}
