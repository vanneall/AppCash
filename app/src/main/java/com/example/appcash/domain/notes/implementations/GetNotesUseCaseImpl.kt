package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetNotesUseCase {
    override fun invoke(folderId: Long?): Flow<List<Note>> {
        return if (folderId != null) {
            repository.getNotes(folderId)
        } else {
            repository.getNotes()
        }
    }
}