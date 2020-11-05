package com.broxus.blockchainexplorer

import zio.Has

package object repository {
  type DBBlockRepository            = Has[DBBlockRepository.Service]
  type DBTransactionRepository      = Has[DBTransactionRepository.Service]
  type DBMessageRepository          = Has[DBMessageRepository.Service]
  type DBAccountRepository          = Has[DBAccountRepository.Service]
  type DBStakeTransactionRepository = Has[DBStakeTransactionRepository.Service]
  type DBTgUserRepository           = Has[DBTgUserRepository.Service]
}
