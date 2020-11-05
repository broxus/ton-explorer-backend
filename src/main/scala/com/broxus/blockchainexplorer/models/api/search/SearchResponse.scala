package com.broxus.blockchainexplorer.models.api.search

import com.broxus.blockchainexplorer.models.api.account.{ Account, AccountId }
import com.broxus.blockchainexplorer.models.api.blocks.BlockListItem
import com.broxus.blockchainexplorer.models.api.message.MessageListItem
import com.broxus.blockchainexplorer.models.api.transactions.TransactionListItem

case class SearchResponse(
    blocks: Seq[BlockListItem],
    messages: Seq[SearchResponseMessageItem],
    transaction: Option[TransactionListItem],
    accounts: Seq[Account],
    validator: Option[AccountId]
)

case class SearchResponseMessageItem(
    block: BlockListItem,
    transaction: TransactionListItem,
    message: MessageListItem
)
