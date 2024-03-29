package com.example.appcash.view.tasks.task.components

import com.example.appcash.data.entities.TaskWithTask

data class TasksState(
    val folderName: String = "",
    val querySearch: String = "",
    val values: List<TaskWithTask> = emptyList(),
    val isError: Boolean = false,
    val isShowed: Pair<Boolean, Boolean> = Pair(false, false),
)