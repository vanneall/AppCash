package com.example.appcash.view.tasks.all_tasks.components

import com.example.appcash.data.entities.Category

data class AllTasksState(
    val searchQuery: String = "",
    val categories: List<Category> = emptyList(),
    val plannedTasks: Int = 0,
    val completeTasks: Int = 0,
    val isShowed: Boolean = false,
    val isError: Boolean = false,
)