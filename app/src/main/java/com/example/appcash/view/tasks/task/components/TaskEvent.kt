package com.example.appcash.view.tasks.task.components

import com.example.appcash.data.entities.Task
import com.example.appcash.utils.events.Event

sealed class TaskEvent: Event {
    data class CreateTaskEvent(
        val mainTaskId: Long? = null,
        val title: String
    ): TaskEvent()

    data class UpdateCheckBoxEvent(
        val task: Task
    ): TaskEvent()
}
