package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note

interface UpdateNoteUseCase {

    fun invoke(note: Note)

}