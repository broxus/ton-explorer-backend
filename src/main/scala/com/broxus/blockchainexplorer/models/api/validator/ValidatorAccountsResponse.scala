package com.broxus.blockchainexplorer.models.api.validator

import com.broxus.blockchainexplorer.models.api.account.AccountId

case class ValidatorAccountsResponse(publicKeyToAccountId: Map[String, AccountId])
