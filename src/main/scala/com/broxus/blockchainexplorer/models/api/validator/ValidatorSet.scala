package com.broxus.blockchainexplorer.models.api.validator

import com.broxus.ton.TonApi

case class ValidatorSet(
    utimeSince: Int,
    utimeUntil: Int,
    total: Int,
    main: Int,
    totalWeight: BigDecimal,
    list: Seq[Validator]
)

object ValidatorSet {
  def apply(s: TonApi.LiteServerValidatorSet): ValidatorSet =
    ValidatorSet(
      utimeSince = s.utimeSince,
      utimeUntil = s.utimeUntil,
      total = s.total,
      main = s.main,
      totalWeight = s.totalWeight,
      list = s.list.map(Validator(_)).toSeq
    )
}
