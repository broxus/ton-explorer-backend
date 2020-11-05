package com.broxus.blockchainexplorer.models.auth

case class AuthInfo(
    auth: Boolean,
    user: Option[UserInfo]
)

case class UserInfo(
    id: Long,
    firstName: String,
    lastName: Option[String],
    username: Option[String],
    photoUrl: Option[String]
)
