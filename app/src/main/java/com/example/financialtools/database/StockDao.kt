package com.example.financialtools.database

import androidx.room.*
import com.example.financialtools.WealthTracker.StockResult
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Query("SELECT * FROM stockentity")
    fun getAll(): Flow<List<StockEntity>>

    @Query("SELECT * FROM stockentity")
    suspend fun initGetAll(): List<StockEntity>

    @Query("UPDATE stockentity SET price=:price WHERE symbol=:symbol")
    suspend fun updateStockPrice(price: Double, symbol: String)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg stocks: StockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: StockEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateStock(stock: StockEntity)

    @Delete
    fun deleteStock(stock: StockEntity)

}