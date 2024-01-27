package com.example.appcash.view.tasks.task.components

import com.example.appcash.utils.events.Event

sealed class TaskEvent: Event {
    data class CreateMaintaskEvent(
        val text: String
    ): TaskEvent()

    data class CreateSubtaskEvent(
        val mainTaskId: Long,
        val text: String
    ): TaskEvent()

    object ShowMaintaskBottomSheetEvent: TaskEvent()
    object ShowSubtaskBottomSheetEvent: TaskEvent()

    data class UpdateCheckBoxEvent(
        val id: Long,
        val isChecked: Boolean,
        val type: TaskType
    ): TaskEvent()
}

enum class TaskType {
    MAIN,
    SUB
}
