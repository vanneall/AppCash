package com.example.appcash.view.tasks.all_tasks.components

import com.example.appcash.data.dto.FolderDto

data class AllTasksState(
    val searchQuery: String = "",
    val folders: List<FolderDto> = emptyList(),
    val plannedTasks: Int = 0,
    val completeTasks: Int = 0,
    val isShowed: Boolean = false,
    val isError: Boolean = false,
)