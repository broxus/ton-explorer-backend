package com.broxus.blockchainexplorer

import zio.Has

package object ton {
  type TonService = Has[TonService.Service]
}
