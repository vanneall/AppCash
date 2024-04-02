package ru.point.data.data.entities

import androidx.room.Relation

data class TaskWithTask(
    val id: Long,
    val text: String,
    val isCompleted: Boolean,
    @Relation(parentColumn = "id", entityColumn = "parent_id")
    val subtasks: List<Task>
)