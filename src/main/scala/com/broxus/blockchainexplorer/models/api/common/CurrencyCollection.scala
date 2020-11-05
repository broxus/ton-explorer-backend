package com.broxus.blockchainexplorer.models.api.common

import java.math.BigInteger

import com.broxus.ton.TonApi

case class CurrencyCollection(
    grams: String,
    other: Map[Int, String]
)

object CurrencyCollection {
  def apply(s: TonApi.LiteServerCurrencyCollection): CurrencyCollection =
    CurrencyCollection(
      grams = new BigInteger(s.grams).toString,
      other = s.other.map(item => (item.currency, new BigInteger(item.value).toString)).toMap
    )
}
