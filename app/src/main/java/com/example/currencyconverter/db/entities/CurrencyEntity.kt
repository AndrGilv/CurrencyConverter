package com.example.data.entities

import androidx.room.*
import com.example.data.models.Currency

@Entity(tableName = "currency",
    foreignKeys = [ForeignKey(
        entity = CurrencyReportEntity::class,
        parentColumns = ["id"],
        childColumns = ["report_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "num_code") val numCode: String,
    @ColumnInfo(name = "char_code") val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    @ColumnInfo(name = "prev_value") val prevValue: Double,
    @ColumnInfo(name = "report_id") val reportId: Int = 1
) {
    fun fromEntity() = Currency(
        this.id,
        this.numCode,
        this.charCode,
        this.nominal,
        this.name,
        this.value,
        this.prevValue
    )
}