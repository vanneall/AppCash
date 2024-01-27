package com.example.appcash.view.tasks.task.components

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask

data class TasksState(
    val folderName: String = "",
    val querySearch: String = "",
    val values: Map<MainTask, List<SubTask>?> = emptyMap(),
    val isError: Boolean = false,
    val isShowed: Pair<Boolean, Boolean> = Pair(false, false),
)