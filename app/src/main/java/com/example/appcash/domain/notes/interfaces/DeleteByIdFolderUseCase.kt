package com.example.appcash.domain.notes.interfaces

import com.example.appcash.utils.events.Event

interface DeleteByIdFolderUseCase {
    fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit)
}