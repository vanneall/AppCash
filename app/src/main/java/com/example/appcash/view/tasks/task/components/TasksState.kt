package com.example.appcash.view.tasks.task.components

import com.example.appcash.view.popup.ConfigPopupState
import com.example.appcash.view.popup.EditPopupState
import ru.point.data.data.entities.TaskWithTask

data class TasksState(
    val folderName: String = "",
    val configPopupState: ConfigPopupState = ConfigPopupState(),
    val editPopupState: EditPopupState = EditPopupState(),
    val values: List<TaskWithTask> = emptyList(),
)