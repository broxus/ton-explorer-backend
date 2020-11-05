package com.broxus.blockchainexplorer.http.endpoints

import com.broxus.blockchainexplorer.models.api.common.{ ApiError, CountResponse }
import com.broxus.blockchainexplorer.models.api.validator._
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

trait StakeTransactionEndpoints {

  def listStakeTransactionsEndpoint: ZEndpoint[StakeTransactionListRequest, ApiError, List[StakeTransactionListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[StakeTransactionListRequest])
      .post
      .in("stake-transaction")
      .in("list")
      .out(jsonBody[List[StakeTransactionListItem]])

  def listStakeTransactionsElectionStakesEndpoint
      : ZEndpoint[ElectionStakesRequest, ApiError, List[StakeTransactionListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[ElectionStakesRequest])
      .post
      .in("stake-transaction")
      .in("election-stakes")
      .out(jsonBody[List[StakeTransactionListItem]])

  def listTotalStakesEndpoint: ZEndpoint[TotalStakesRequest, ApiError, Seq[TotalStakeListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[TotalStakesRequest])
      .post
      .in("stake-transactions")
      .in("total-stakes")
      .out(jsonBody[Seq[TotalStakeListItem]])

  def countStakeTransactionsEndpoint: ZEndpoint[StakeTransactionCountRequest, ApiError, CountResponse] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[StakeTransactionCountRequest])
      .post
      .in("stake-transaction")
      .in("count")
      .out(jsonBody[CountResponse])

  def getValidatorsAccounts: ZEndpoint[ValidatorAccountsRequest, ApiError, ValidatorAccountsResponse] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[ValidatorAccountsRequest])
      .post
      .in("validator")
      .in("get-accounts")
      .out(jsonBody[ValidatorAccountsResponse])

  def electionDatesEndpoint: ZEndpoint[ElectionDatesRequest, ApiError, List[Int]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[ElectionDatesRequest])
      .post
      .in("election-dates-list")
      .out(jsonBody[List[Int]])

}
