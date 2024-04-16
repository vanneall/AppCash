package com.example.appcash.view.tasks.list.components

import com.example.appcash.view.popup.taskconfigurator.TaskConfiguratorPopupState
import com.example.appcash.view.popup.taskcontroll.TaskControlPopupState
import ru.point.data.data.entity.entities.TaskWithTask

data class TasksListState(
    val screenTitle: String = "",
    val tasks: List<TaskWithTask> = emptyList(),
    val taskControlPopupState: TaskControlPopupState = TaskControlPopupState(),
    val taskConfiguratorPopupState: TaskConfiguratorPopupState = TaskConfiguratorPopupState(),
)