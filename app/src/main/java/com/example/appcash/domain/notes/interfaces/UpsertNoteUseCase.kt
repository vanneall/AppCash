package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note

interface UpsertNoteUseCase {
    fun invoke(note: Note, folderId: Long)

    fun invoke(note: Note)
}