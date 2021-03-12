package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.CurrencyEntity
import com.example.data.entities.CurrencyReportEntity
import com.example.data.entities.CurrencyReportWithCurrencyList


@Database(entities = [CurrencyEntity::class, CurrencyReportEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDAO
    abstract fun currencyReportDao(): CurrencyReportDAO

    companion object{
        val NAME = "app_db"
    }
}
