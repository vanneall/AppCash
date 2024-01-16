package com.example.appcash.view.tasks.task.components

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask

data class TasksState(
    val folderName: String = "",
    val values: Map<MainTask, List<SubTask>?> = emptyMap(),
    val querySearch: String = ""
)