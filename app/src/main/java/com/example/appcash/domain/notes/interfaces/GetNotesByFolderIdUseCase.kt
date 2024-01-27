package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow

interface GetNotesByFolderIdUseCase {
    fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit): Flow<List<Note>>
}