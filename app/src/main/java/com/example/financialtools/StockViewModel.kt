package com.example.financialtools

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.financialtools.WealthTracker.StockData
import com.example.financialtools.WealthTracker.StockResult
import com.example.financialtools.WealthTracker.YahooFinanceApi
import com.example.financialtools.database.StockDao
import com.example.financialtools.database.StockEntity
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

class StockViewModel(private val stockDao: StockDao): ViewModel() {

    suspend fun insertStock(stock: StockEntity) = stockDao.insert(stock)

    fun deleteStock(stock: StockEntity) = stockDao.deleteStock(stock)

    fun getAll(): Flow<List<StockEntity>> = stockDao.getAll()

    suspend fun retrieveStocksDb(): List<StockEntity> = stockDao.initGetAll()


    suspend fun getStocks(stocks: List<StockEntity>): List<StockResult> {
        val symbol = stocks.joinToString(",")
        Log.d("ViewModel", "Combined symbols: $symbol")
        val stockData = YahooFinanceApi.retrofitService.getQuote(symbol)
        if (stockData.quoteResponse.error==null) {
            return stockData.quoteResponse.results
        }
        return listOf()
    }

    suspend fun updateDbPrices(stocks: List<StockResult>) {

        for (stock in stocks) {
            stockDao.updateStockPrice(stock.price, stock.symbol)
        }
    }
}



class StockViewModelFactory(private val stockDao: StockDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return StockViewModel(stockDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

