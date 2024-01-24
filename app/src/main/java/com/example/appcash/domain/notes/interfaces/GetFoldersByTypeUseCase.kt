package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow

interface GetFoldersByTypeUseCase {
    fun invoke(type: FolderType, onError: (ErrorEvent) -> Unit): Flow<List<Folder>>
}