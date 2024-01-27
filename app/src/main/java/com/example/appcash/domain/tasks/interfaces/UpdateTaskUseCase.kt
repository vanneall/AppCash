package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.utils.events.Event
import com.example.appcash.view.tasks.task.components.TaskType

interface UpdateTaskUseCase {
    fun invoke(
        id: Long,
        isChecked: Boolean,
        type: TaskType,
        onError: (Event.ErrorEvent) -> Unit
    )
}