package com.example.appcash.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = MainTask::class,
            parentColumns = ["id"],
            childColumns = ["mainTaskId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubTask::class,
            parentColumns = ["id"],
            childColumns = ["subTaskId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SubTaskToMainTaskLink(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,

    @ColumnInfo(name = "mainTaskId")
    val mainTaskId: Long,

    @ColumnInfo(name = "subTaskId")
    val subTaskId: Long
)