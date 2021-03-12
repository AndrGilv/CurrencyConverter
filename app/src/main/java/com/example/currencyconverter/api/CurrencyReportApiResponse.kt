package com.example.data.api


import com.example.data.models.Currency
import com.example.data.models.CurrencyReport
import com.squareup.moshi.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class CurrencyReportApiResponse(
    @Json(name = "Date") val date: String,
    @Json(name = "PreviousDate") val prevDate: String,
    @Json(name = "PreviousURL")val prevUrl: String,
    @Json(name = "Timestamp")val timestamp: String,
    @Json(name = "Valute")val currenciesMap: Map<String,CurrencyResponse>
){
    fun fromResponse() = CurrencyReport(
        LocalDateTime.parse(this.date, DateTimeFormatter.ISO_DATE_TIME),
        LocalDateTime.parse(this.prevDate, DateTimeFormatter.ISO_DATE_TIME),
        this.prevUrl,
        LocalDateTime.parse(this.timestamp, DateTimeFormatter.ISO_DATE_TIME),
        this.currenciesMap.values.map { it.fromResponse() }
    )
}

data class CurrencyResponse(
    @Json(name = "ID")val id: String,
    @Json(name = "NumCode")val numCode: String,
    @Json(name = "CharCode")val charCode: String,
    @Json(name = "Nominal")val nominal: Int,
    @Json(name = "Name")val name: String,
    @Json(name = "Value")val value: Double,
    @Json(name = "Previous")val prevValue: Double
){
    fun fromResponse() = Currency(
        this.id,
        this.numCode,
        this.charCode,
        this.nominal,
        this.name,
        this.value,
        this.prevValue
    )
}