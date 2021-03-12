package com.example.data.repositories.currency_exchange_report

import com.example.data.models.CurrencyReport

interface CurrencyExchangeReportRemoteDataSource {

    suspend fun getCurrencyReport(): CurrencyReport
}