package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.utils.events.Event

interface UpdateTaskUseCase {
    fun invoke(
        id: Long,
        isChecked: Boolean,
        onError: (Event.ErrorEvent) -> Unit
    )
}