package com.example.data.repositories.currency_exchange_report

import com.example.data.models.CurrencyReport

interface CurrencyExchangeReportRepo {
    suspend fun getCurrencyReport(): CurrencyReport
    suspend fun isCurrencyReportRelevant(): Boolean
}