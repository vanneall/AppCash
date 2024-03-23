package com.example.appcash.data.entities

import androidx.room.Relation

data class TaskWithTask(
    val id: Long,
    val text: String,
    val isCompleted: Boolean,
    @Relation(parentColumn = "id", entityColumn = "category_id")
    val subtasks: List<Task>
)