package com.broxus.blockchainexplorer.repository

import com.broxus.blockchainexplorer.models.auth.UserProfile
import com.broxus.blockchainexplorer.repository.models.DBTgUser
import zio.Task

object DBTgUserRepository {
  trait Service {
    def findUser(id: Long): Task[Option[DBTgUser]]
    def saveAuth(profile: UserProfile): Task[Int]
  }
}
