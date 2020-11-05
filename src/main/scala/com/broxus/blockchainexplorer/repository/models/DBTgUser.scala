package com.broxus.blockchainexplorer.repository.models

case class DBTgUser(
    id: Long,
    firstName: String,
    lastName: Option[String],
    username: Option[String],
    photoUrl: Option[String],
    lastAuthDate: Long,
    languageCode: Option[String]
)
