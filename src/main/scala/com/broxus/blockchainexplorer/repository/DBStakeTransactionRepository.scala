package com.broxus.blockchainexplorer.repository

import cats.data.NonEmptyList
import com.broxus.blockchainexplorer.repository.models.DBStakeTransaction
import zio.Task

object DBStakeTransactionRepository {
  trait Service {
    def list(
        limit: Int,
        offset: Long,
        fromTs: Option[Int],
        toTs: Option[Int],
        account: Option[(Int, Array[Byte])],
        electionTime: Option[Int]
    ): Task[Seq[DBStakeTransaction]]

    def listElectionStakes(electionTime: Int): Task[Seq[DBStakeTransaction]]

    def listAllStakeTransactions: Task[Seq[DBStakeTransaction]]

    def count(
        fromTs: Option[Int],
        toTs: Option[Int],
        account: Option[(Int, Array[Byte])],
        electionTime: Option[Int]
    ): Task[Long]

    def elections(fromTs: Option[Int], toTs: Option[Int]): Task[Seq[Int]]

    def getAccountsByPublicKeys(publicKeys: NonEmptyList[Array[Byte]]): Task[Seq[(Array[Byte], Int, Array[Byte])]]

    def nextElectionsWithEmptyMaxFactor(): Task[Option[Int]]

    def updateStakeTransactionAndlAndMaxFactor(item: DBStakeTransaction): Task[Int]
  }
}
