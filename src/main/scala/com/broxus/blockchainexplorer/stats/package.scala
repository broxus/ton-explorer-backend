package com.broxus.blockchainexplorer

import zio.Has

package object stats {
  type StatsService = Has[StatsService.Service]
}
