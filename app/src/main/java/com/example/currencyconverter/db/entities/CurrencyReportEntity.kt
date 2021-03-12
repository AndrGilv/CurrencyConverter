package com.example.data.entities

import androidx.room.*
import com.example.data.db.DateConverter
import com.example.data.models.CurrencyReport
import java.time.LocalDateTime

@Entity(tableName = "currency_report")
@TypeConverters(DateConverter::class)
data class CurrencyReportEntity(
    val date: LocalDateTime,
    @ColumnInfo(name = "prev_date") val prevDate: LocalDateTime,
    @ColumnInfo(name = "prev_url") val prevURL: String,
    val timestamp: LocalDateTime,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
) {
    fun fromEntity() = CurrencyReport(
        this.date,
        this.prevDate,
        this.prevURL,
        this.timestamp,
        listOf()
    )
}

data class CurrencyReportWithCurrencyList(
    @Embedded val currencyReport: CurrencyReportEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "report_id",
        entity=CurrencyEntity::class
    )
    val currencyRatesList: List<CurrencyEntity>
){
    fun fromEntity() = CurrencyReport(
        this.currencyReport.date,
        this.currencyReport.prevDate,
        this.currencyReport.prevURL,
        this.currencyReport.timestamp,
        this.currencyRatesList.map {
            it.fromEntity()
        }
    )
}
