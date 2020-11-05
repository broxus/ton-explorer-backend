package com.broxus.blockchainexplorer

import java.util.Base64

object AccountAddressHelper {
  def urlUnSafe(address: String): String =
    address
      .replaceAll("-", "+")
      .replaceAll("_", "/")

  def rawAddress(workchain: Int, addr: Array[Byte]): String =
    workchain.toString + ':' + HexHelper.convertBytesToHex(addr)

  def unpackAddress(address: String): (Int, Array[Byte], Boolean) = {
    val bytes = Base64.getDecoder.decode(urlUnSafe(address))
    (bytes(1).toInt, bytes.slice(2, bytes.length - 2), (bytes.head | 0x40) == 0)
  }

  def packAddress(workchain: Int, addr: Array[Byte], bounceable: Boolean): String = {
    val part1 = Array((0x51 - (if (bounceable) 0x40 else 0)).toByte, workchain.toByte) ++ addr
    val crc16 = helpers.CRC16.calculate(part1)
    Base64
      .getEncoder
      .encodeToString(part1 ++ packCrc16(crc16))
  }

  def addressBase64Bounceable(address: String): String = {
    val (workcahin, addr, _) = unpackAddress(address)
    packAddress(workcahin, addr, bounceable = false)
  }

  def addressBase64NonBounceable(address: String): String = {
    val (workcahin, addr, _) = unpackAddress(address)
    packAddress(workcahin, addr, bounceable = true)
  }

  def packCrc16(crc16: Int): Array[Byte] = Array((crc16 >> 8).toByte, crc16.toByte)
}
