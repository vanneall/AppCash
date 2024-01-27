package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.utils.events.Event

interface InsertMaintaskUseCase {
    operator fun invoke(folderId: Long, text: String, onError: (Event.ErrorEvent) -> Unit)
}