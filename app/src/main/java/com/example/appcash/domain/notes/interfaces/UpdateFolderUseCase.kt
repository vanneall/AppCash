package com.example.appcash.domain.notes.interfaces

import com.example.appcash.utils.events.Event

interface UpdateFolderUseCase {
    fun invoke(id: Long, name: String, colorIndex: Int, onError: (Event.ErrorEvent) -> Unit)
}