package com.example.appcash.view.tasks.task.components

import com.example.appcash.utils.events.Event

sealed class TaskEvent: Event {
    data class CreateTask(
        val text: String,
        val parentTaskId: Long
    ): TaskEvent()

    data class UpdateCompletedState(
        val id: Long,
        val isChecked: Boolean
    ): TaskEvent()

    object ShowTaskBottomSheetEvent: TaskEvent()
}
