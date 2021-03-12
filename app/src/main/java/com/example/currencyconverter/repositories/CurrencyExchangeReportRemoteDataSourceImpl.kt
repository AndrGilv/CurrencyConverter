package com.example.data.repositories.currency_exchange_report

import com.example.data.api.CurrencyReportApiService
import com.example.data.models.CurrencyReport
import javax.inject.Inject

class CurrencyExchangeReportRemoteDataSourceImpl @Inject constructor(
    val apiService: CurrencyReportApiService
): CurrencyExchangeReportRemoteDataSource {

    override suspend fun getCurrencyReport(): CurrencyReport {
        val temp = apiService.getDailyCurrencyReport()
        return temp.fromResponse()
    }
}