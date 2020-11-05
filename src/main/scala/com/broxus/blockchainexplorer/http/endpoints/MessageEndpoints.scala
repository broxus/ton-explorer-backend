package com.broxus.blockchainexplorer.http.endpoints

import com.broxus.blockchainexplorer.models.api.common.{ ApiError, CountResponse, LimitedIdRequest }
import com.broxus.blockchainexplorer.models.api.message.{
  Message,
  MessageListItem,
  MessagesCountRequest,
  MessagesRequest
}
import com.broxus.blockchainexplorer.models.api.search.{ SearchRequest, SearchResponse, SearchResponseMessageItem }
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

trait MessageEndpoints {
  def getMessageEndpoint: ZEndpoint[LimitedIdRequest, ApiError, List[Message]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[LimitedIdRequest])
      .post
      .in("message")
      .out(jsonBody[List[Message]])

  def listMessagesEndpoint: ZEndpoint[MessagesRequest, ApiError, List[MessageListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[MessagesRequest])
      .post
      .in("message")
      .in("list")
      .out(jsonBody[List[MessageListItem]])

  def countMessagesEndpoint: ZEndpoint[MessagesCountRequest, ApiError, CountResponse] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[MessagesCountRequest])
      .post
      .in("message")
      .in("count")
      .out(jsonBody[CountResponse])

  def searchMessagesEndpoint: ZEndpoint[SearchRequest, ApiError, List[SearchResponseMessageItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .in(jsonBody[SearchRequest])
      .post
      .in("search")
      .in("message")
      .out(jsonBody[List[SearchResponseMessageItem]])

}
