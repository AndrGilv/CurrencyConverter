package com.example.currencye_exchange_rate_app.di

import com.example.data.api.CurrencyReportApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent ::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCurrencyReportRetrofitInstance(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideCurrencyReportApi(retrofit: Retrofit): CurrencyReportApiService {
        return retrofit.create(CurrencyReportApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}