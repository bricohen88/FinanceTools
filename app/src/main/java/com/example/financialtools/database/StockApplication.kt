package com.example.financialtools.database

import android.app.Application

class StockApplication: Application() {

    val database: StockDatabase by lazy {StockDatabase.getDatabase(this)}
}