package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface GetNoteByIdUseCase {

    fun invoke(id: Long): Flow<Note>

}