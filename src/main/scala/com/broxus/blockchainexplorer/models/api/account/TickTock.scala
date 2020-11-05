package com.broxus.blockchainexplorer.models.api.account

import com.broxus.ton.TonApi

case class TickTock(
    tick: Boolean,
    tock: Boolean
)

object TickTock {
  def apply(s: TonApi.LiteServerTickTock): TickTock =
    TickTock(
      tick = s.tick,
      tock = s.tock
    )
}
