package com.broxus.blockchainexplorer.repository.db

import cats.data.NonEmptyList
import com.broxus.blockchainexplorer.repository.DBStakeTransactionRepository
import com.broxus.blockchainexplorer.repository.models.DBStakeTransaction
import doobie.hikari.HikariTransactor
import doobie.util.transactor.Transactor
import zio.{ Task, ZIO, ZLayer }
import doobie.implicits._
import zio.interop.catz._

final class DBStakeTransactionRepositoryImpl(xa: Transactor[Task]) extends DBStakeTransactionRepository.Service {

  override def list(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      electionTime: Option[Int]
  ): Task[Seq[DBStakeTransaction]] =
    SQL
      .listStakeTransactions(limit, offset, fromTs, toTs, account, electionTime)
      .to[Vector]
      .transact(xa)

  def listElectionStakes(electionTime: Int): Task[Seq[DBStakeTransaction]] =
    SQL
      .listElectionStakes(electionTime)
      .to[Vector]
      .transact(xa)

  def listAllStakeTransactions: Task[Seq[DBStakeTransaction]] =
    SQL
      .listAllStakeTransactions
      .to[Vector]
      .transact(xa)

  override def count(
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      electionTime: Option[Int]
  ): Task[Long] =
    SQL
      .countStakeTransactions(fromTs, toTs, account, electionTime)
      .unique
      .transact(xa)

  override def elections(fromTs: Option[Int], toTs: Option[Int]): Task[Seq[Int]] =
    SQL.elections(fromTs, toTs).to[Vector].transact(xa)

  override def getAccountsByPublicKeys(
      publicKeys: NonEmptyList[Array[Byte]]
  ): Task[Seq[(Array[Byte], Int, Array[Byte])]] =
    SQL.getAccountsByPublicKeys(publicKeys).to[Vector].transact(xa)

  override def nextElectionsWithEmptyMaxFactor(): Task[Option[Int]] =
    SQL.nextElectionsWithEmptyMaxFactor.option.transact(xa)

  override def updateStakeTransactionAndlAndMaxFactor(item: DBStakeTransaction): Task[Int] =
    SQL.updateStakeTransactionAndlAndMaxFactor(item).run.transact(xa)
}

object DBStakeTransactionRepositoryImpl {
  val layer: ZLayer[DBTransactor, Throwable, DBStakeTransactionRepository] = ZLayer.fromManaged {
    for {
      transactor <- ZIO.service[HikariTransactor[Task]].toManaged_
    } yield new DBStakeTransactionRepositoryImpl(transactor)
  }
}
