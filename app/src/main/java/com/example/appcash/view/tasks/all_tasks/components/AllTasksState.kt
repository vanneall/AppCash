package com.example.appcash.view.tasks.all_tasks.components

import com.example.appcash.data.entities.Folder

data class AllTasksState(
    val searchQuery: String = "",
    val folders: List<Folder> = emptyList(),
    val plannedTasks: Int = 0,
    val completeTasks: Int = 0
)