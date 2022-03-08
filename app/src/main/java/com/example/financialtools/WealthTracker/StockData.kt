package com.example.financialtools.WealthTracker

import com.squareup.moshi.Json

data class StockData(
    @Json(name = "quoteResponse")
    val quoteResponse: QuoteResult
)