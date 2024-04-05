package com.example.appcash.view.tasks.list.components

import com.example.appcash.utils.events.Event

sealed class TaskListEvent : Event {
    data class UpdateCompletedState(
        val id: Long,
        val isChecked: Boolean
    ) : TaskListEvent()

    data class UpdateBookmarked(
        val id: Long
    ) : TaskListEvent()
}
