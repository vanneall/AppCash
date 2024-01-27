package com.example.appcash.domain.notes.interfaces

import com.example.appcash.utils.events.Event.ErrorEvent
import com.example.appcash.view.notes.note_info.components.NoteOpenMode

interface UpsertNoteUseCase {
    fun invoke(
        noteId: Long? = null,
        title: String,
        content: String,
        openMode: NoteOpenMode,
        onError: (ErrorEvent) -> Unit,
        folderId: Long? = null,
    )
}