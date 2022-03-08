package com.example.financialtools.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


@Database(entities = [StockEntity::class], version = 1)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var INSTANCE: StockDatabase? = null

        fun getDatabase(
            context: Context
        ): StockDatabase {
            return INSTANCE ?: synchronized(this) {
                return if(INSTANCE == null) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockDatabase::class.java,
                        "stock_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                } else
                    INSTANCE as StockDatabase
            }
        }

    }
}

