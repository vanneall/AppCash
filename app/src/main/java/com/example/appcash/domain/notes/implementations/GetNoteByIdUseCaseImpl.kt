package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.interfaces.GetNoteByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
): GetNoteByIdUseCase {
    override fun invoke(id: Long): Flow<Note> {
        return repository.getNoteById(id = id)
    }
}