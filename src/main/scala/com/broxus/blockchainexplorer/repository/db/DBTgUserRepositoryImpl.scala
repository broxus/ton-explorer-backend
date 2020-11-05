package com.broxus.blockchainexplorer.repository.db

import com.broxus.blockchainexplorer.models.auth.UserProfile
import com.broxus.blockchainexplorer.repository.DBTgUserRepository
import com.broxus.blockchainexplorer.repository.models.DBTgUser
import doobie.hikari.HikariTransactor
import doobie.implicits._
import doobie.util.transactor.Transactor
import zio.interop.catz._
import zio.{ Task, ZIO, ZLayer }

final class DBTgUserRepositoryImpl(xa: Transactor[Task]) extends DBTgUserRepository.Service {

  override def saveAuth(profile: UserProfile): Task[Int] = {
    val newTgUser = DBTgUser(
      id = profile.id,
      firstName = profile.firstName,
      lastName = profile.lastName,
      username = profile.username,
      photoUrl = profile.photoUrl,
      lastAuthDate = profile.authDate,
      languageCode = None
    )

    SQL.upsertTgUser(newTgUser).run.transact(xa)
  }

  override def findUser(id: Long): Task[Option[DBTgUser]] = SQL.findTgUser(id).option.transact(xa)
}

object DBTgUserRepositoryImpl {
  val layer: ZLayer[DBTransactor, Throwable, DBTgUserRepository] = ZLayer.fromManaged {
    for {
      transactor <- ZIO.service[HikariTransactor[Task]].toManaged_
    } yield new DBTgUserRepositoryImpl(transactor)
  }
}
