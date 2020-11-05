package com.broxus.blockchainexplorer.repository

import com.broxus.blockchainexplorer.repository.models._
import zio.Task

object DBBlockRepository {
  trait Service {

    def upsertBatch(
        blocks: List[DBBlock],
        transactions: List[DBTransaction],
        messages: List[DBMessage],
        stakeTransactions: List[DBStakeTransaction]
    ): Task[Int]

    def search(query: String): Task[Seq[DBBlock]]

    def search(id: Int, shard: String, seqno: Int): Task[Option[DBBlock]]

    def list(limit: Int, offset: Long, fromTs: Option[Int], toTs: Option[Int], skipEmpty: Boolean): Task[Seq[DBBlock]]

    def getTopBlocks(): Task[Seq[(Int, Long, Int)]]

    def count(fromTs: Option[Int]): Task[Seq[(Int, Long, Long)]]

    def maxSeqno(): Task[Int]

  }
}
