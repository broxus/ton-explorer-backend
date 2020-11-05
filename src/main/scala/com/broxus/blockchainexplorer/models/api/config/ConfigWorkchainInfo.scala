package com.broxus.blockchainexplorer.models.api.config

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.ton.TonApi

case class ConfigWorkchainInfo(
    enabledSince: Int,
    actualMinSplit: Int,
    minSplit: Int,
    maxSplit: Int,
    basic: Boolean,
    active: Boolean,
    acceptMsgs: Boolean,
    flags: Int,
    zerostateRootHash: String,
    zerostateFileHash: String,
    version: Int,
    format: ConfigWorkchainFormat
)

object ConfigWorkchainInfo {
  def apply(s: TonApi.LiteServerConfigWorkchainInfo): ConfigWorkchainInfo =
    ConfigWorkchainInfo(
      enabledSince = s.enabledSince,
      actualMinSplit = s.actualMinSplit,
      minSplit = s.minSplit,
      maxSplit = s.maxSplit,
      basic = s.basic,
      active = s.active,
      acceptMsgs = s.acceptMsgs,
      flags = s.flags,
      zerostateRootHash = HexHelper.convertBytesToHex(s.zerostateRootHash),
      zerostateFileHash = HexHelper.convertBytesToHex(s.zerostateFileHash),
      version = s.version,
      format = ConfigWorkchainFormat(s.format)
    )
}
