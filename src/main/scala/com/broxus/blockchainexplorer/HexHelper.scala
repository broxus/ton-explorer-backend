package com.broxus.blockchainexplorer

import scala.util.Try

object HexHelper {
  def convertBytesToHex(bytes: Array[Byte]): String = {
    val sb = new StringBuilder
    for (b <- bytes)
      sb.append(String.format("%02x", Byte.box(b)))
    sb.toString.toUpperCase
  }

  def convertBytesToHexSafe(bytes: Array[Byte]): Option[String] =
    Try(convertBytesToHex(bytes)).toOption

  def convertHexToBytes(hex: String): Array[Byte] =
    hex.toSeq.sliding(2, 2).toArray.map(e => Integer.parseInt(e.unwrap, 16).toByte)

  def convertHexToBytesSafe(mayBeHex: String): Option[Array[Byte]] =
    Try(convertHexToBytes(mayBeHex)).toOption
}
