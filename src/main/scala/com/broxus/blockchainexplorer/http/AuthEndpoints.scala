package com.broxus.blockchainexplorer.http

import com.broxus.blockchainexplorer.models.api.common.ApiError
import com.broxus.blockchainexplorer.models.auth.{ AuthInfo, UserInfo, UserProfile }
import io.circe.generic.auto._
import sttp.model.{ Cookie, CookieValueWithMeta }
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, endpoint, extractFromRequest, stringBody, _ }

import scala.util.Try

object AuthEndpoints {
  val SESSION_COOKIE = "session"

  def authorizeRedirect: ZEndpoint[Option[UserProfile], String, CookieValueWithMeta] =
    endpoint
      .errorOut(stringBody)
      .get
      .in("authorizeRedirect")
      .in(extractFromRequest[Option[UserProfile]] { rq =>
        val pairs = rq.uri.getQuery.split("&|=").grouped(2)
        val map   = pairs.map { case Array(k, v) => k -> v }.toMap
        Try {
          UserProfile(
            id = map("id").toLong,
            firstName = map("first_name"),
            authDate = map("auth_date").toLong,
            lastName = map.get("last_name"),
            username = map.get("username"),
            photoUrl = map.get("photo_url"),
            hash = map("hash")
          )
        }.toOption
      })
      .out(statusCode(sttp.model.StatusCode.apply(302)))
      .out(header("Location", "/"))
      .out(setCookie(SESSION_COOKIE))

  def authInfoEndpoint: ZEndpoint[Option[String], ApiError, AuthInfo] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(cookie[Option[String]](SESSION_COOKIE))
      .get
      .in("auth-info")
      .out(jsonBody[AuthInfo])
}
