package ru.point.data.data.entity.subset

import androidx.room.ColumnInfo
import java.time.LocalDate

data class LocalDateSubset(
    @ColumnInfo("date")
    val date: LocalDate? = null
) {
    fun isValuesNull(): Boolean {
        return date == null
    }
}