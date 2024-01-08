package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.interfaces.UpdateNoteUseCase
import javax.inject.Inject

class UpdateNoteUseCaseImpl @Inject constructor(
    val repository: NoteRepository
): UpdateNoteUseCase {
    override fun invoke(note: Note) {
        repository.updateNote(note = note)
    }
}