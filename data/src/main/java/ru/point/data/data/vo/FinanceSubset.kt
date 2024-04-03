package ru.point.data.data.vo

import androidx.room.ColumnInfo

data class FinanceSubset(
    @ColumnInfo("id")
    val id: Long? = null,
    @ColumnInfo("name")
    val folderName: String? = null,
    @ColumnInfo("icon")
    val icon: String? = null,
    @ColumnInfo("price")
    val price: Int? = null
)