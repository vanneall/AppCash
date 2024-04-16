package ru.point.data.data.entity.entities

import androidx.room.ColumnInfo
import androidx.room.Relation
import java.time.LocalDate

data class TaskWithTask(
    val id: Long,
    val text: String,
    val isCompleted: Boolean,
    @ColumnInfo("date")
    val date: LocalDate? = null,
    @ColumnInfo("is_bookmark")
    val isBookmarked: Boolean,
    @ColumnInfo("description")
    val description: String,
    @Relation(parentColumn = "id", entityColumn = "parent_id")
    val subtasks: List<Task>
)