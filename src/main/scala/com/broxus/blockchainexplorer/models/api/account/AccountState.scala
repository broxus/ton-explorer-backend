package com.broxus.blockchainexplorer.models.api.account

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class AccountState(
    kind: String,
    splitDepth: Option[Int] = None,
    special: Option[TickTock] = None,
    code: Option[String] = None,
    data: Option[String] = None,
    library: Map[String, SimpleLib] = Map(),
    stateHash: Option[String] = None
)

object AccountState {
  def apply: TonApi.LiteServerAccountState => AccountState = {
    case _: TonApi.LiteServerAccountStateUninit =>
      AccountState(
        kind = "uninit"
      )
    case s: TonApi.LiteServerAccountStateActive =>
      AccountState(
        kind = "active",
        splitDepth = Option.when(s.hasSplitDepth)(s.splitDepth),
        special = Option(s.special).map(TickTock(_)),
        code = Option.when(s.hasCode)(HexHelper.convertBytesToHex(s.code)),
        data = Option.when(s.hasData)(HexHelper.convertBytesToHex(s.data)),
        library = s.library.map(item => (HexHelper.convertBytesToHex(item.key), SimpleLib(item))).toMap
      )
    case s: TonApi.LiteServerAccountStateFrozen =>
      AccountState(
        kind = "frozen",
        stateHash = Some(HexHelper.convertBytesToHex(s.stateHash))
      )
  }
}
