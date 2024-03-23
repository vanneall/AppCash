package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.utils.events.Event

interface InsertMaintaskUseCase {
    operator fun invoke(
        text: String,
        parentTaskId: Long? = null,
        folderId: Long? = null,
        onError: (Event.ErrorEvent) -> Unit
    )
}