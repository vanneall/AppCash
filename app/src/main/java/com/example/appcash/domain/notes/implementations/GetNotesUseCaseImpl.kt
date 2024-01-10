package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
): GetNotesUseCase {
    override fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}