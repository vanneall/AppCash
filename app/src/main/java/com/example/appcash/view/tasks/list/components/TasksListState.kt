package com.example.appcash.view.tasks.list.components

import com.example.appcash.view.popup.taskconfigurator.TaskConfiguratorPopupState
import com.example.appcash.view.popup.taskcontroll.TaskControlPopupState
import ru.point.data.data.entities.TaskWithTask

data class TasksListState(
    val screenTitle: String = "",
    val taskControlPopupState: TaskControlPopupState = TaskControlPopupState(),
    val taskConfiguratorPopupState: TaskConfiguratorPopupState = TaskConfiguratorPopupState(),
    val tasks: List<TaskWithTask> = emptyList(),
)