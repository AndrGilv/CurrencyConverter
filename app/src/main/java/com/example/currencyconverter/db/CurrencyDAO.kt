package com.example.data.db

import androidx.room.*
import com.example.data.entities.CurrencyEntity

@Dao
interface CurrencyDAO {
    @Query("SELECT * FROM 'currency'")
    suspend fun getAll(): List<CurrencyEntity>

    @Insert
    suspend fun insertAll(vararg currencyEntities: CurrencyEntity)

    @Update
    suspend fun updateAll(currenciesList: List<CurrencyEntity>)

    @Delete
    suspend fun delete(currencyEntity: CurrencyEntity)
}
