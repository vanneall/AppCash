package com.example.appcash.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("text")
    val text: String,

    @ColumnInfo("isCompleted")
    val isCompleted: Boolean
)