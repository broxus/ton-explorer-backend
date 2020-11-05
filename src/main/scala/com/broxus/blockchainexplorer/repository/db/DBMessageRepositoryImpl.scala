package com.broxus.blockchainexplorer.repository.db

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.DBMessageRepository
import com.broxus.blockchainexplorer.repository.models.{ DBBlock, DBMessage, DBTransaction }
import doobie.hikari.HikariTransactor
import doobie.implicits._
import doobie.util.transactor.Transactor
import zio.interop.catz._
import zio.{ Task, ZIO, ZLayer }

import scala.util.Try

final class DBMessageRepositoryImpl(xa: Transactor[Task]) extends DBMessageRepository.Service {

  override def search(query: String, limit: Int): Task[Seq[(DBMessage, DBTransaction, DBBlock)]] =
    Try(HexHelper.convertHexToBytes(query)).toOption match {
      case Some(hash) =>
        SQL
          .searchMessageByHash(hash, limit)
          .to[Vector]
          .transact(xa)
      case _          => ZIO.succeed(Nil)
    }

  override def count(
      fromTs: Option[Int],
      toTs: Option[Int],
      out: Option[Boolean],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]]
  ): Task[Long] =
    SQL
      .messagesCount(fromTs, toTs, out, account, excludeAccounts)
      .unique
      .transact(xa)

  override def list(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]]
  ): Task[Seq[DBMessage]] =
    SQL
      .listMessages(limit, offset, fromTs, toTs, account, excludeAccounts)
      .to[Vector]
      .transact(xa)

  override def volume(fromTs: Option[Int] = None): Task[BigDecimal] =
    SQL
      .volume(fromTs)
      .unique
      .transact(xa)
}

object DBMessageRepositoryImpl {
  val layer: ZLayer[DBTransactor, Throwable, DBMessageRepository] = ZLayer.fromManaged {
    for {
      transactor <- ZIO.service[HikariTransactor[Task]].toManaged_
    } yield new DBMessageRepositoryImpl(transactor)
  }
}
