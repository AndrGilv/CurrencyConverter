package com.example.data.api

import retrofit2.http.GET

interface CurrencyReportApiService {

    @GET("daily_json.js")
    suspend fun getDailyCurrencyReport(): CurrencyReportApiResponse
}