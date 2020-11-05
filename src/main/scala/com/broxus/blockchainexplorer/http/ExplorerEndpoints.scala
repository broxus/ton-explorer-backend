package com.broxus.blockchainexplorer.http

import com.broxus.blockchainexplorer.http.endpoints.{
  AccountEndpoints,
  BlockEndpoints,
  MessageEndpoints,
  StakeTransactionEndpoints,
  TransactionEndpoints,
  ValidatorEndpoints
}
import com.broxus.blockchainexplorer.models.api.common.ApiError
import com.broxus.blockchainexplorer.models.api.search.{ SearchRequest, SearchResponse }
import com.broxus.blockchainexplorer.models.api.stats.{ BlockchainStats, GetTimeResponse }
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

object ExplorerEndpoints
    extends AccountEndpoints
    with TransactionEndpoints
    with MessageEndpoints
    with BlockEndpoints
    with ValidatorEndpoints
    with StakeTransactionEndpoints {

  def getTimeEndpoint: ZEndpoint[Unit, ApiError, GetTimeResponse] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .get
      .in("get-time")
      .out(jsonBody[GetTimeResponse])

  def blockchainStatsEndpoint: ZEndpoint[Unit, ApiError, BlockchainStats] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .get
      .in("stats")
      .out(jsonBody[BlockchainStats])

  def searchEndpoint: ZEndpoint[SearchRequest, ApiError, SearchResponse] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[SearchRequest])
      .post
      .in("search")
      .out(jsonBody[SearchResponse])

}
