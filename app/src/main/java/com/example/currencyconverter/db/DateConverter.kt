package com.example.data.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

class DateConverter {
    @TypeConverter
    fun toLocalDate(seconds: Long): LocalDateTime = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC)

    @TypeConverter
    fun toLong(date: LocalDateTime): Long = date.toEpochSecond(ZoneOffset.UTC)
}