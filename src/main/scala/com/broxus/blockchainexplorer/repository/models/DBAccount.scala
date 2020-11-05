package com.broxus.blockchainexplorer.repository.models

case class DBAccount(workchain: Int, address: Array[Byte], balance: BigDecimal, created: Int, updated: Int)
