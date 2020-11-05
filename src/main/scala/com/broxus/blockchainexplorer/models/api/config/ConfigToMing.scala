package com.broxus.blockchainexplorer.models.api.config

import java.math.BigInteger

import com.broxus.ton.TonApi

object ConfigToMing {
  def apply(s: TonApi.LiteServerConfigToMint): Map[Int, String] =
    s.currencyCollection.map(item => (item.currency, new BigInteger(item.value).toString)).toMap
}
