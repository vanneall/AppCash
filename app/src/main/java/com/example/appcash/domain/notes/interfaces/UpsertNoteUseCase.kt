package com.example.appcash.domain.notes.interfaces

import com.example.appcash.utils.events.Event
import com.example.appcash.view.notes.note_info.components.NoteOpenMode

interface UpsertNoteUseCase {
    fun invoke(
        title: String,
        content: String,
        onError: (Event.ErrorEvent) -> Unit,
        folderId: Long? = null,
    )

    fun invoke(
        id: Long,
        title: String,
        content: String,
        onError: (Event.ErrorEvent) -> Unit,
        folderId: Long? = null,
    )
}