package com.example.data.repositories.currency_exchange_report

import com.example.currencyconverter.toEntity
import com.example.data.db.CurrencyDAO
import com.example.data.db.CurrencyReportDAO
import com.example.data.models.Currency
import com.example.data.models.CurrencyReport
import java.time.LocalDateTime
import javax.inject.Inject

class CurrencyExchangeReportLocalDataSourceImpl @Inject constructor(
    val currencyReportDAO: CurrencyReportDAO,
    val currencyDAO: CurrencyDAO
) : CurrencyExchangeReportLocalDataSource {


    override suspend fun getCurrenciesList(): List<Currency> {
        return currencyDAO.getAll().map { it.fromEntity() }
    }

    override suspend fun getCurrencyReport(): CurrencyReport {
        return currencyReportDAO.getLastExtendedReport().fromEntity()
    }

    override suspend fun isCurrencyReportExpired(): Boolean {
        return currencyReportDAO.getLastReport().date < LocalDateTime.now()
    }

    override suspend fun updateCurrencyReport(currencyReport: CurrencyReport) {
        val currencyReportEntity = currencyReport.toEntity()
        val list = currencyReport.currencyRateList.map { it.toEntity() }
        currencyReportDAO.update(currencyReportEntity)
        currencyDAO.updateAll(list)
    }
}