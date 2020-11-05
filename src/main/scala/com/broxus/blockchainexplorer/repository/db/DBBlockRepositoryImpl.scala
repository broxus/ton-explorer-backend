package com.broxus.blockchainexplorer.repository.db

import java.math.BigInteger

import com.broxus.blockchainexplorer.HexHelper
import com.broxus.blockchainexplorer.repository.DBBlockRepository
import com.broxus.blockchainexplorer.repository.models.{ DBBlock, DBMessage, DBStakeTransaction, DBTransaction }
import doobie.hikari.HikariTransactor
import doobie.implicits._
import doobie.util.transactor.Transactor
import zio.interop.catz._
import zio.{ Task, ZIO, ZLayer }
import cats.implicits._
import doobie.free.connection
import doobie.free.connection.ConnectionIO

import scala.util.Try

final class DBBlockRepositoryImpl(xa: Transactor[Task]) extends DBBlockRepository.Service {

  def apply[A](f: => A): ConnectionIO[A] = connection.pure(f)

  override def upsertBatch(
      blocks: List[DBBlock],
      transactions: List[DBTransaction],
      messages: List[DBMessage],
      stakeTransactions: List[DBStakeTransaction]
  ): Task[Int] =
    (SQL.upsertBlocks(blocks) >>
      SQL.upsertTransactions(transactions) >>
      SQL.upsertMessages(messages) >>
      SQL.upsertStakeTransactions(stakeTransactions)).transact(xa)

  override def getTopBlocks(): Task[Seq[(Int, Long, Int)]] =
    SQL
      .getLatestIndexed()
      .to[Vector]
      .transact(xa)

  override def search(query: String): Task[Seq[DBBlock]] =
    (Try(query.toInt).toOption, HexHelper.convertHexToBytesSafe(query)) match {
      case (Some(seqno), _) =>
        SQL
          .searchBlockBySeqno(seqno)
          .to[Vector]
          .transact(xa)
      case (_, Some(hash))  =>
        SQL
          .searchBlockByHash(hash)
          .to[Vector]
          .transact(xa)
      case _                => ZIO.succeed(Nil)
    }

  def search(workchain: Int, shard: String, seqno: Int): Task[Option[DBBlock]] =
    Option.when(shard.length <= 16)(Try(HexHelper.convertHexToBytes(shard)).toOption) match {
      case Some(Some(shard)) =>
        SQL
          .searchBlockByFullSeqno(workchain, new BigInteger(shard).longValue(), seqno)
          .option
          .transact(xa)
      case _                 => ZIO.none
    }

  override def list(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      skipEmpty: Boolean
  ): Task[Seq[DBBlock]] =
    SQL
      .listBlocks(limit, offset, fromTs, toTs, skipEmpty)
      .to[Vector]
      .transact(xa)

  override def count(fromTs: Option[Int]): Task[Seq[(Int, Long, Long)]] =
    SQL
      .blocksCount(fromTs)
      .to[Vector]
      .transact(xa)

  override def maxSeqno(): Task[Int] =
    SQL
      .maxSeqno()
      .unique
      .transact(xa)
}

object DBBlockRepositoryImpl {
  val layer: ZLayer[DBTransactor, Throwable, DBBlockRepository] = ZLayer.fromManaged {
    for {
      transactor <- ZIO.service[HikariTransactor[Task]].toManaged_
    } yield new DBBlockRepositoryImpl(transactor)
  }
}
