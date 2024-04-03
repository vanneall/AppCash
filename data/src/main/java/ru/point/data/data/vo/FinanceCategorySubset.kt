package ru.point.data.data.vo

import androidx.room.ColumnInfo

data class FinanceCategorySubset(
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("color")
    val color: Int? = null,
    @ColumnInfo("sum")
    val sum: Int? = null
)
