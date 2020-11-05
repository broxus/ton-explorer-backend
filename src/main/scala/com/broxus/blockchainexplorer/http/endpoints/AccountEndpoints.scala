package com.broxus.blockchainexplorer.http.endpoints

import com.broxus.blockchainexplorer.models.api.account.{
  Account,
  AccountListItem,
  AccountTransactionsRequest,
  AccountsRequest
}
import com.broxus.blockchainexplorer.models.api.common.{ ApiError, IdRequest }
import com.broxus.blockchainexplorer.models.api.transactions.Transaction
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

trait AccountEndpoints {

  def getAccountEndpoint: ZEndpoint[IdRequest, ApiError, Account] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[IdRequest])
      .post
      .in("account")
      .out(jsonBody[Account])

  val getAccountTransactionsEndpoint: ZEndpoint[AccountTransactionsRequest, ApiError, List[Transaction]] = endpoint
    .errorOut(jsonBody[ApiError])
    .in("api")
    .in(jsonBody[AccountTransactionsRequest])
    .post
    .in("account")
    .in("transactions")
    .out(jsonBody[List[Transaction]])

  val listAccountsEndpoint: ZEndpoint[AccountsRequest, ApiError, List[AccountListItem]] = endpoint
    .errorOut(jsonBody[ApiError])
    .in("api")
    .in(jsonBody[AccountsRequest])
    .post
    .in("account")
    .in("list")
    .out(jsonBody[List[AccountListItem]])

}
