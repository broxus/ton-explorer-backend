package com.broxus.blockchainexplorer.repository

import com.broxus.blockchainexplorer.repository.models._
import zio.Task

object DBTransactionRepository {
  trait Service {

    def search(query: String): Task[Option[(DBTransaction, DBBlock)]]

    def list(
        limit: Int,
        offset: Long,
        fromTs: Option[Int],
        toTs: Option[Int],
        account: Option[(Int, Array[Byte])],
        excludeAccounts: Option[Seq[(Int, Array[Byte])]],
        transactionTypes: Option[Seq[String]]
    ): Task[Seq[DBTransaction]]

    def count(
        fromTs: Option[Int],
        toTs: Option[Int],
        account: Option[(Int, Array[Byte])],
        excludeAccounts: Option[Seq[(Int, Array[Byte])]],
        transactionTypes: Option[Seq[String]]
    ): Task[Long]
  }
}
