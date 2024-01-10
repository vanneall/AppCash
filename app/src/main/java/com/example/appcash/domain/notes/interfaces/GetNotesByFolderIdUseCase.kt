package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface GetNotesByFolderIdUseCase {
    fun invoke(id: Long): Flow<List<Note>>
}