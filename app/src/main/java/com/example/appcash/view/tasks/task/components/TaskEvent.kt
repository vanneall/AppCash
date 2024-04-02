package com.example.appcash.view.tasks.task.components

import com.example.appcash.utils.events.Event

sealed class TaskEvent: Event {
    data class UpdateCompletedState(
        val id: Long,
        val isChecked: Boolean
    ): TaskEvent()

    object CreateSubtask: TaskEvent()
}
