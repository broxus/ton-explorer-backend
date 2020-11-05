package com.broxus.blockchainexplorer.http.endpoints

import com.broxus.blockchainexplorer.models.api.blocks.{ Block, BlockListItem, BlockRequest, BlockState, BlocksRequest }
import com.broxus.blockchainexplorer.models.api.common.{ ApiError, IdRequest }
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

trait BlockEndpoints {

  def getBlockEndpoint: ZEndpoint[IdRequest, ApiError, Block] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[IdRequest])
      .post
      .in("block")
      .out(jsonBody[Block])

  def getBlockBySeqnoEndpoint: ZEndpoint[BlockRequest, ApiError, Block] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[BlockRequest])
      .post
      .in("block")
      .in("by-seqno")
      .out(jsonBody[Block])

  def getBlockLatestEndpoint: ZEndpoint[Unit, ApiError, Block] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .post
      .in("block")
      .in("latest")
      .out(jsonBody[Block])

  def listBlocksEndpoint: ZEndpoint[BlocksRequest, ApiError, List[BlockListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[BlocksRequest])
      .post
      .in("block")
      .in("list")
      .out(jsonBody[List[BlockListItem]])

  def getZeroState: ZEndpoint[Unit, ApiError, BlockState] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .post
      .in("block")
      .in("zerostate")
      .out(jsonBody[BlockState])

}
