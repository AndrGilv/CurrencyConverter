package com.example.currencye_exchange_rate_app.di

import com.example.data.api.CurrencyReportApiService
import com.example.data.db.CurrencyDAO
import com.example.data.db.CurrencyReportDAO
import com.example.data.repositories.currency_exchange_report.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {

    @Provides
    fun provideLocalDataSourceImpl(currencyDAO: CurrencyDAO, currencyReportDAO: CurrencyReportDAO): CurrencyExchangeReportLocalDataSource {
        return CurrencyExchangeReportLocalDataSourceImpl(currencyReportDAO, currencyDAO)
    }

    @Provides
    fun provideRemoteDataSourceImpl(currencyReportApiService: CurrencyReportApiService): CurrencyExchangeReportRemoteDataSource {
        return CurrencyExchangeReportRemoteDataSourceImpl(currencyReportApiService)
    }


    @Provides
    fun provideCurrencyExchangeReportRepoImpl(localDataSourceImpl: CurrencyExchangeReportLocalDataSourceImpl, remoteDataSourceImpl: CurrencyExchangeReportRemoteDataSourceImpl): CurrencyExchangeReportRepo {
        return CurrencyExchangeReportRepoImpl(localDataSourceImpl, remoteDataSourceImpl)
    }
}