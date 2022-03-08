package com.example.financialtools.WealthTracker

import com.squareup.moshi.Json

data class QuoteResult(
    @Json(name = "result")
    val results: MutableList<StockResult>,
    @Json(name ="error")
    val error: Object?)