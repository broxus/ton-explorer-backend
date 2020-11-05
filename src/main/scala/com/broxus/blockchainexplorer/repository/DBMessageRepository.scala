package com.broxus.blockchainexplorer.repository

import com.broxus.blockchainexplorer.repository.models._
import zio.Task

object DBMessageRepository {
  trait Service {

    def search(query: String, limit: Int): Task[Seq[(DBMessage, DBTransaction, DBBlock)]]

    def list(
        limit: Int,
        offset: Long,
        fromTs: Option[Int],
        toTs: Option[Int],
        account: Option[(Int, Array[Byte])],
        excludeAccounts: Option[Seq[(Int, Array[Byte])]]
    ): Task[Seq[DBMessage]]

    def count(
        fromTs: Option[Int],
        toTs: Option[Int],
        out: Option[Boolean],
        account: Option[(Int, Array[Byte])],
        excludeAccounts: Option[Seq[(Int, Array[Byte])]]
    ): Task[Long]

    def volume(fromTs: Option[Int] = None): Task[BigDecimal]

  }
}
