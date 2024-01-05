package com.example.appcash.view.notes.notes_list.components

import com.example.appcash.view.Event

sealed class NoteListEvent : Event {
    object CreateNoteEvent : NoteListEvent()

    data class SearchNoteEvent(
        val searchQuery: String
    ) : NoteListEvent()

    data class OpenNoteEvent(
        val noteId: Long
    ) : NoteListEvent()
}
