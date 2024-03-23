package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {
    fun invoke(folderId: Long?): Flow<List<Note>>
}