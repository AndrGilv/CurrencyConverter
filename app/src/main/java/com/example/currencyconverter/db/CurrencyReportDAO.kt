package com.example.data.db

import androidx.room.*
import com.example.data.entities.CurrencyReportEntity
import com.example.data.entities.CurrencyReportWithCurrencyList

@Dao
interface CurrencyReportDAO {
    @Transaction
    @Query("SELECT * FROM currency_report")
    suspend fun getAll(): List<CurrencyReportEntity>

    @Transaction
    @Query("SELECT * FROM currency_report order by date desc limit 1")
    suspend fun getLastReport(): CurrencyReportEntity

    @Transaction
    @Query("SELECT * FROM currency_report order by date desc limit 1")
    suspend fun getLastExtendedReport(): CurrencyReportWithCurrencyList

    @Insert
    suspend fun insertAll(vararg currencyReportEntities: CurrencyReportEntity)

    @Update
    suspend fun update(currencyReportEntities: CurrencyReportEntity): Int

    @Delete
    suspend fun delete(currencyReportEntities: CurrencyReportEntity)
}
