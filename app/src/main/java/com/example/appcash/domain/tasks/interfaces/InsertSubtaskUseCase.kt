package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.utils.events.Event

interface InsertSubtaskUseCase {
    fun invoke(mainTaskId: Long, text: String, onError: (Event.ErrorEvent) -> Unit)
}