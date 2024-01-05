package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {
    fun invoke(id: Long): Flow<List<Note>>
}