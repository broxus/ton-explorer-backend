package com.broxus.blockchainexplorer

import zio._

object Main extends App {

  def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    Layers
      .live
      .appLayer
      .build
      .useForever
      .exitCode

}
