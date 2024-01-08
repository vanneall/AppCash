package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.interfaces.InsertNoteUseCase
import javax.inject.Inject

class InsertNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : InsertNoteUseCase {
    override fun invoke(note: Note, folderId: Long) {
        repository.insertNote(
            note = note,
            folderId = folderId
        )
    }
}