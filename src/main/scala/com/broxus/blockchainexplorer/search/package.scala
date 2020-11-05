package com.broxus.blockchainexplorer

import zio.Has

package object search {
  type SearchService = Has[SearchService.Service]
}
