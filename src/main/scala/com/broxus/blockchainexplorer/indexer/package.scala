package com.broxus.blockchainexplorer

import zio.Has

package object indexer {
  type IndexerService = Has[IndexerService.Service]
}
