package com.broxus.blockchainexplorer.repository

import doobie.hikari.HikariTransactor
import zio.{ Has, Task }

package object db {
  type DBTransactor = Has[HikariTransactor[Task]]
}
