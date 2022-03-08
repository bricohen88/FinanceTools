package com.example.financialtools.WealthTracker

import com.squareup.moshi.Json


data class StockResult(
    @Json(name = "regularMarketPrice")
    val price: Double,
    @Json( name = "shortName")
    val shortName: String,
    @Json(name = "symbol")
    val symbol: String

    )
