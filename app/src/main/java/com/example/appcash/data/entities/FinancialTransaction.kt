package com.example.appcash.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.YearMonth

@Entity
data class FinancialTransaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,

    @ColumnInfo("price")
    val price: Int,

    @ColumnInfo("date")
    val date: YearMonth
)
