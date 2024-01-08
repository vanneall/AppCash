package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.interfaces.UpsertNoteUseCase
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository,
) : UpsertNoteUseCase {
    override fun invoke(note: Note, folderId: Long) {
        repository.insertNote(
            note = note,
            folderId = folderId
        )
    }

    override fun invoke(note: Note) {
        repository.updateNote(
            note = note
        )
    }
}