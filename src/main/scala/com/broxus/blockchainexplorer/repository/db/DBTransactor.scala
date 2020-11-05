package com.broxus.blockchainexplorer.repository.db

import cats.effect.Blocker
import com.broxus.blockchainexplorer.config.{ DBConfig, DBConfigProvider }
import doobie.hikari.HikariTransactor
import org.flywaydb.core.Flyway
import zio.{ Managed, Task, ZIO, ZLayer, ZManaged }
import zio.blocking.Blocking
import zio.interop.catz._

object DBTransactor {

  val layer: ZLayer[Blocking with DBConfigProvider, Throwable, DBTransactor] = {

    def initDb(cfg: DBConfig): Task[Unit] =
      Task {
        Flyway
          .configure()
          .dataSource(cfg.url, cfg.user, cfg.password)
          .load()
          .migrate()
      }.unit

    def mkTransactor(
        cfg: DBConfig
    ): ZManaged[Blocking, Throwable, HikariTransactor[Task]] =
      ZIO.runtime[Blocking].toManaged_.flatMap { implicit rt =>
        for {
          transactEC <- Managed.succeed(
                          rt.environment
                            .get[Blocking.Service]
                            .blockingExecutor
                            .asEC
                        )
          connectEC   = rt.platform.executor.asEC
          transactor <- HikariTransactor
                          .newHikariTransactor[Task](
                            cfg.driver,
                            cfg.url,
                            cfg.user,
                            cfg.password,
                            connectEC,
                            Blocker.liftExecutionContext(transactEC)
                          )
                          .toManaged
        } yield transactor
      }

    ZLayer.fromManaged {
      for {
        cfg        <- ZIO.service[DBConfig].toManaged_
        _          <- initDb(cfg).toManaged_
        transactor <- mkTransactor(cfg)
      } yield transactor
    }
  }

}
