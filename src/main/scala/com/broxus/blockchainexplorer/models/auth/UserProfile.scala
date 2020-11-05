package com.broxus.blockchainexplorer.models.auth

import java.math.BigInteger
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

case class UserProfile(
    id: Long,
    firstName: String,
    lastName: Option[String],
    username: Option[String],
    photoUrl: Option[String],
    authDate: Long,
    hash: String
)

object UserProfile {
  def isValid(profile: UserProfile, authBotToken: String): Boolean = {
    val data = scala
      .collection
      .mutable
      .Map(
        "id"         -> profile.id.toString,
        "first_name" -> profile.firstName,
        "auth_date"  -> profile.authDate.toString
      )

    profile.lastName.foreach(d => data += ("last_name" -> d))
    profile.username.foreach(d => data += ("username" -> d))
    profile.photoUrl.foreach(d => data += ("photo_url" -> d))

    val data_str = data.toSeq.sortBy(_._1).map(i => i._1 + "=" + i._2).mkString("\n")

    val mac        = Mac.getInstance("HmacSHA256")
    val secret_key = new SecretKeySpec(MessageDigest.getInstance("SHA-256").digest(authBotToken.getBytes), "SHA-256")
    mac.init(secret_key)

    val result = f"${new BigInteger(1, mac.doFinal(data_str.getBytes))}%032x"
    result == profile.hash && (profile.authDate > (System.currentTimeMillis() / 1000L) - 86400)
  }
}
