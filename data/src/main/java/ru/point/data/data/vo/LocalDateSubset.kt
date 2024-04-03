package ru.point.data.data.vo

import androidx.room.ColumnInfo
import java.time.LocalDate

data class LocalDateSubset(
    @ColumnInfo("date")
    val date: LocalDate? = null
)