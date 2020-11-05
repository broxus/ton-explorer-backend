package com.broxus.blockchainexplorer.repository.db

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.DBTransactionRepository
import com.broxus.blockchainexplorer.repository.models.{ DBBlock, DBTransaction }
import doobie.hikari.HikariTransactor
import doobie.implicits._
import doobie.util.transactor.Transactor
import zio.interop.catz._
import zio.{ Task, ZIO, ZLayer }

import scala.util.Try

final class DBTransactionRepositoryImpl(xa: Transactor[Task]) extends DBTransactionRepository.Service {

  override def search(query: String): Task[Option[(DBTransaction, DBBlock)]] =
    Try(HexHelper.convertHexToBytes(query)).toOption match {
      case Some(hash) =>
        SQL
          .searchTransactionByHash(hash)
          .option
          .transact(xa)
      case _          => ZIO.none
    }

  override def list(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]],
      transactionTypes: Option[Seq[String]]
  ): Task[Seq[DBTransaction]] =
    SQL
      .listTransactions(limit, offset, fromTs, toTs, account, excludeAccounts, transactionTypes)
      .to[Vector]
      .transact(xa)

  override def count(
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]],
      transactionTypes: Option[Seq[String]]
  ): Task[Long] =
    SQL
      .transactionsCount(fromTs, toTs, account, excludeAccounts, transactionTypes)
      .unique
      .transact(xa)
}

object DBTransactionRepositoryImpl {
  val layer: ZLayer[DBTransactor, Throwable, DBTransactionRepository] = ZLayer.fromManaged {
    for {
      transactor <- ZIO.service[HikariTransactor[Task]].toManaged_
    } yield new DBTransactionRepositoryImpl(transactor)
  }
}
