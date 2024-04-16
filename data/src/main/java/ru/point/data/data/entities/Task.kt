package ru.point.data.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

//Используется вместе с TaskWithTask, потому как у каждой Task может быть своя Task
@Entity(
    tableName = "task",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Task::class,
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

    @ColumnInfo("description")
    val description: String = "",

    @ColumnInfo("parent_id")
    val parentTaskId: Long? = null,

    @ColumnInfo("date")
    val date: LocalDate? = null,

    @ColumnInfo("category_id")
    val categoryId: Long? = null,

    @ColumnInfo("is_bookmark")
    val isBookmark: Boolean = false
)