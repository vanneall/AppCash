package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.DeleteNoteByIdUseCase
import javax.inject.Inject

class DeleteNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : DeleteNoteByIdUseCase {
    override fun invoke(id: Long) {
        repository.deleteNote(id = id)
    }
}