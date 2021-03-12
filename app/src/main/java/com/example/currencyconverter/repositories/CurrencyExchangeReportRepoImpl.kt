package com.example.data.repositories.currency_exchange_report

import com.example.currencyconverter.CurrencyReportLoadingError
import com.example.data.models.CurrencyReport
import javax.inject.Inject

class CurrencyExchangeReportRepoImpl @Inject constructor(
    private val localDataSource: CurrencyExchangeReportLocalDataSource,
    private val remoteDataSource: CurrencyExchangeReportRemoteDataSource
) : CurrencyExchangeReportRepo {

    override suspend fun getCurrencyReport(): CurrencyReport {
        if (localDataSource.isCurrencyReportExpired()) {
            try {
                val newCurrencyReport = remoteDataSource.getCurrencyReport()
                localDataSource.updateCurrencyReport(newCurrencyReport)

                return newCurrencyReport
            } catch (cause: Throwable) {
                throw CurrencyReportLoadingError("Unable to load currency report", cause)
            }
        } else {
            return localDataSource.getCurrencyReport()
        }
    }

    override suspend fun isCurrencyReportRelevant(): Boolean {
        return !localDataSource.isCurrencyReportExpired()
    }
}