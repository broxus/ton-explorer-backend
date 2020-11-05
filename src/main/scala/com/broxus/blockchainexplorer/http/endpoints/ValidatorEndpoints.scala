package com.broxus.blockchainexplorer.http.endpoints

import com.broxus.blockchainexplorer.models.api.common.ApiError
import com.broxus.blockchainexplorer.models.api.validator.{ PastElectionListItem, ValidatorsState }
import io.circe.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{ ZEndpoint, _ }

trait ValidatorEndpoints {
  def getValidatorsPastElectionsEndpoint: ZEndpoint[Unit, ApiError, List[PastElectionListItem]] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .get
      .in("validators")
      .in("past-elections")
      .out(jsonBody[List[PastElectionListItem]])

  def getValidatorsStateEndpoint: ZEndpoint[Unit, ApiError, ValidatorsState] =
    endpoint
      .errorOut(jsonBody[ApiError])
      .in("api")
      .get
      .in("validators")
      .in("state")
      .out(jsonBody[ValidatorsState])
}
