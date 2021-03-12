package com.example.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    var value: Double,
    val prevValue: Double
) : Parcelable {

    fun getActualValue() = value / nominal.toDouble()

    companion object{
        fun emptyObject() = Currency(
            "",
            "",
            "USD",
            1,
            "dollar",
            1.0,
            1.0
        )
    }
}