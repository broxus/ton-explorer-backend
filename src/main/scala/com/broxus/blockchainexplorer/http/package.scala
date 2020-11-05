package com.broxus.blockchainexplorer

import zio.Has

package object http {

  type HealthzService  = Has[HealthzService.Service]
  type ExplorerService = Has[ExplorerService.Service]

  type HttpService = Has[HttpService.Service]

}
