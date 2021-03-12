package com.example.currencye_exchange_rate_app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.db.AppDatabase
import com.example.data.db.CurrencyDAO
import com.example.data.db.CurrencyReportDAO
import com.example.data.entities.CurrencyEntity
import com.example.data.entities.CurrencyReportEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppDataBaseModule {

    lateinit var database: AppDatabase

    @Provides
    fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDAO {
        return appDatabase.currencyDao()
    }

    @Provides
    fun provideCurrencyReportDao(appDatabase: AppDatabase): CurrencyReportDAO {
        return appDatabase.currencyReportDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        database = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                GlobalScope.launch {

                    database.currencyReportDao().insertAll(
                        CurrencyReportEntity(
                            id = 1,
                            date = LocalDateTime.of(2021, 1, 1, 0, 0, 0),
                            prevDate = LocalDateTime.of(2021, 1, 1, 0, 0, 0),
                            prevURL = "",
                            timestamp = LocalDateTime.of(2021, 1, 1, 0, 0, 0)
                        )
                    )

                    database.currencyDao().insertAll(
                        CurrencyEntity(
                            "R01010", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01020A", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01035", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01060", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01090B", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01100", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01115", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01135", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01200", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01215", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01235", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01239", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01270", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01335", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01350", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01370", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01375", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01500", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01535", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01565", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01585F", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01589", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01625", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01670", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01700J", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01710A", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01717", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01720", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01760", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01770", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01775", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01810", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01815", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        ),
                        CurrencyEntity(
                            "R01820", "978", "EUR", 1, "Евро", 88.9334, 88.9421, 1
                        )
                    )
                }
            }
        }).build()
        return database
    }
}

