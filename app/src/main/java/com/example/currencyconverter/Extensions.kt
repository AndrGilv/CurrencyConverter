package com.example.currencyconverter

import com.example.data.entities.CurrencyEntity
import com.example.data.entities.CurrencyReportEntity
import com.example.data.models.Currency
import com.example.data.models.CurrencyReport

fun CurrencyReport.toEntity() = CurrencyReportEntity(
    this.date,
    this.prevDate,
    this.prevURL,
    this.timestamp
)

fun Currency.toEntity() = CurrencyEntity(
    this.id,
    this.numCode,
    this.charCode,
    this.nominal,
    this.name,
    this.value,
    this.prevValue
)