package ru.point.data.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//Используется вместе с TaskWithTask, потому как у каждой Task может быть своя Task
@Entity(
    tableName = "task",
    foreignKeys = [ ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["parent_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,

    @ColumnInfo("text")
    val text: String,

    @ColumnInfo("isCompleted")
    val isCompleted: Boolean = false,

    @ColumnInfo("parent_id")
    val parentId: Long? = null,

    @ColumnInfo("category_id")
    val folderId: Long? = null
)