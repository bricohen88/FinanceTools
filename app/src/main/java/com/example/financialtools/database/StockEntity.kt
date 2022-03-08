package com.example.financialtools.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StockEntity(
    @PrimaryKey val symbol: String,
    @ColumnInfo(name="short_name") val shortName: String,
    @ColumnInfo(name="price") val price: Double,
    @ColumnInfo(name="shares") val shares: Double)

{
    override fun toString(): String {
        return symbol
    }
}
