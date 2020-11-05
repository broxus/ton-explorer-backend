package com.broxus.blockchainexplorer.http

import com.broxus.blockchainexplorer.http.HttpService.RoutesService
import org.http4s._
import sttp.tapir.server.http4s.ztapir._
import sttp.tapir.{ endpoint, stringBody }
import zio._
import zio.interop.catz._

object HealthzService {

  sealed trait Service extends RoutesService[Task]

  val layer: ZLayer[Any, Throwable, HealthzService] = ZLayer.succeed(
    new Service {
      val routes: HttpRoutes[Task] = {

        val health = endpoint
          .errorOut(stringBody)
          .get
          .in("healthz")
          .out(stringBody)
          .toRoutes(_ => IO.succeed(""))

        health
      }
    }
  )

}
