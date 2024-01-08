package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note

interface InsertNoteUseCase {
    fun invoke(note: Note, folderId: Long)
}