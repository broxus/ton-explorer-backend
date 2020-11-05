package com.broxus.blockchainexplorer.search.models

import com.broxus.ton.TonApi

case class SearchResult(
    blocks: Seq[TonApi.LiteServerBlock],
    transactions: Seq[TonApi.LiteServerTransaction],
    messages: Seq[TonApi.LiteServerMessage]
)
