package ru.point.data.data.entities

import androidx.room.ColumnInfo
import androidx.room.Relation

data class TaskWithTask(
    val id: Long,
    val text: String,
    val isCompleted: Boolean,
    @ColumnInfo("is_bookmark")
    val isBookmarked: Boolean,
    @ColumnInfo("description")
    val description: String,
    @Relation(parentColumn = "id", entityColumn = "parent_id")
    val subtasks: List<Task>
)