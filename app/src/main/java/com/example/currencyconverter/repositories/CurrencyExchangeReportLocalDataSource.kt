package com.example.data.repositories.currency_exchange_report

import com.example.data.models.Currency
import com.example.data.models.CurrencyReport

interface CurrencyExchangeReportLocalDataSource {

    suspend fun getCurrenciesList(): List<Currency>
    suspend fun getCurrencyReport(): CurrencyReport
    suspend fun isCurrencyReportExpired(): Boolean
    suspend fun updateCurrencyReport(currencyReport: CurrencyReport)
}