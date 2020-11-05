package com.broxus.blockchainexplorer.http.endpoints

import com.broxus.blockchainexplorer.models.api.transactions._
import com.broxus.blockchainexplorer.models.api.common.{ ApiError, CountResponse, IdRequest }
import com.broxus.blockchainexplorer.models.api.transactions.Transaction
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

trait TransactionEndpoints {
  def getTransactionEndpoint: ZEndpoint[IdRequest, ApiError, Transaction] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[IdRequest])
      .post
      .in("transaction")
      .out(jsonBody[Transaction])

  def listTransactionsEndpoint: ZEndpoint[TransactionsRequest, ApiError, List[TransactionListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[TransactionsRequest])
      .post
      .in("transaction")
      .in("list")
      .out(jsonBody[List[TransactionListItem]])

  def countTransactionsEndpoint: ZEndpoint[TransactionsCountRequest, ApiError, CountResponse] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[TransactionsCountRequest])
      .post
      .in("transaction")
      .in("count")
      .out(jsonBody[CountResponse])
}
