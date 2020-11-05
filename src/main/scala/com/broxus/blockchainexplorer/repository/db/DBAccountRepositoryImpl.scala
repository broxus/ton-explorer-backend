package com.broxus.blockchainexplorer.repository.db

import com.broxus.blockchainexplorer.repository.DBAccountColumn.DBAccountColumn
import com.broxus.blockchainexplorer.repository.{ DBAccountColumn, DBAccountRepository }
import com.broxus.blockchainexplorer.repository.models.DBAccount
import doobie.hikari.HikariTransactor
import doobie.util.transactor.Transactor
import zio.{ Task, ZIO, ZLayer }
import doobie.implicits._
import zio.interop.catz._

final class DBAccountRepositoryImpl(xa: Transactor[Task]) extends DBAccountRepository.Service {

  override def list(
      limit: Int,
      offset: Long,
      orderColumn: DBAccountColumn,
      asc: Boolean,
      fromTs: Option[Int],
      toTs: Option[Int]
  ): Task[Seq[DBAccount]] =
    SQL
      .listAccounts(limit, offset, orderColumn, asc, fromTs, toTs)
      .to[Vector]
      .transact(xa)

  override def totalSupply(): Task[BigDecimal] =
    SQL
      .totalSupply()
      .unique
      .transact(xa)

  override def countCreated(fromTs: Option[Int]): Task[Long] =
    SQL
      .accountsCreated(fromTs)
      .unique
      .transact(xa)

  override def countUpdated(fromTs: Option[Int]): Task[Long] =
    SQL
      .countUpdated(fromTs)
      .unique
      .transact(xa)

  override def upsert(items: List[DBAccount]): Task[Int] = SQL.upsertAccounts(items).transact(xa)
}

object DBAccountRepositoryImpl {
  val layer: ZLayer[DBTransactor, Throwable, DBAccountRepository] = ZLayer.fromManaged {
    for {
      transactor <- ZIO.service[HikariTransactor[Task]].toManaged_
    } yield new DBAccountRepositoryImpl(transactor)
  }
}
