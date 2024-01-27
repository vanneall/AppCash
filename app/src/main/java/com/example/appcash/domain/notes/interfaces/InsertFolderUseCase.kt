package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.FolderType
import com.example.appcash.utils.events.Event.ErrorEvent

interface InsertFolderUseCase {
    fun invoke(name: String, colorIndex: Int, type: FolderType, onError: (ErrorEvent) -> Unit)
}