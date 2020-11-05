package com.broxus.blockchainexplorer.repository

import com.broxus.blockchainexplorer.repository.DBAccountColumn.DBAccountColumn
import com.broxus.blockchainexplorer.repository.models._
import zio.Task

import scala.util.Try

object DBAccountRepository {
  trait Service {
    def upsert(items: List[DBAccount]): Task[Int]

    def list(
        limit: Int,
        offset: Long,
        orderColumn: DBAccountColumn,
        asc: Boolean,
        fromTs: Option[Int],
        toTs: Option[Int]
    ): Task[Seq[DBAccount]]

    def countCreated(fromTs: Option[Int]): Task[Long]

    def countUpdated(fromTs: Option[Int]): Task[Long]

    def totalSupply(): Task[BigDecimal]
  }
}
object DBAccountColumn extends Enumeration {
  type DBAccountColumn = Value

  val created: DBAccountColumn = Value("created")
  val updated: DBAccountColumn = Value("updated")
  val balance: DBAccountColumn = Value("balance")

  def find(code: String): Option[DBAccountColumn] =
    Try {
      DBAccountColumn.withName(code)
    }.toOption
}
