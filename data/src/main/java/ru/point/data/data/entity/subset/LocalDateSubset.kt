package ru.point.data.data.entity.subset

import androidx.room.ColumnInfo
import java.time.LocalDate

data class LocalDateSubset(
    @ColumnInfo("max_date")
    val maxDate: LocalDate,
    @ColumnInfo("min_date")
    val date: LocalDate
)