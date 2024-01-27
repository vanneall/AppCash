package com.example.appcash.domain.notes.interfaces

import com.example.appcash.utils.events.Event

interface DeleteNoteByIdUseCase {
    fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit)
}