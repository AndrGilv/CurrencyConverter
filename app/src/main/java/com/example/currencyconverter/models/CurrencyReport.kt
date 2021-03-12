package com.example.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class CurrencyReport(
    val date: LocalDateTime,
    val prevDate: LocalDateTime,
    val prevURL: String,
    val timestamp: LocalDateTime,
    val currencyRateList: List<Currency>
) : Parcelable {
    companion object {
        fun emptyCurrencyReport() = CurrencyReport(
            LocalDateTime.now(),
            LocalDateTime.now(),
            "",
            LocalDateTime.now(),
            listOf()
        )

        suspend fun testCurrencyReport() = CurrencyReport(
            LocalDateTime.of(2021, 1, 2, 0, 0, 0),
            LocalDateTime.of(2021, 1, 2, 0, 0, 0),
            "https://prev-url/daily-report.json",
            LocalDateTime.of(2021, 1, 2, 0, 0, 0),
            listOf(
                Currency(
                    "R01010",
                    "036",
                    "AUD",
                    1,
                    "Австралийский доллар",
                    57.1826,
                    57.5681
                ),
                Currency(
                    "R01020A",
                    "944",
                    "AZN",
                    1,
                    "Азербайджанский манат",
                    43.8067,
                    43.4293
                ),
                Currency(
                    "R01035",
                    "826",
                    "GBP",
                    1,
                    "Фунт стерлингов Соединенного королевства",
                    103.1342,
                    102.9099
                ),
                Currency(
                    "R01060",
                    "051",
                    "AMD",
                    100,
                    "Армянских драмов",
                    14.1807,
                    14.0345
                ),
                Currency(
                    "R01235",
                    "840",
                    "USD",
                    1,
                    "Доллар США",
                    74.4275,
                    73.7864
                )/*,
                Currency(
                    "R0${Random.nextInt(0,2000)}",
                    "${Random.nextInt(0,200)}",
                    "RC",
                    Random.nextInt(1,100),
                    "Random coin",
                    Random.nextDouble(10.0, 150.0),
                    Random.nextDouble(10.0, 150.0)
                )*/
            )
        )
    }

    fun getByCharCode(charCode: String): Currency = currencyRateList.firstOrNull() {
        it.charCode == charCode
    } ?: Currency.emptyObject()
}