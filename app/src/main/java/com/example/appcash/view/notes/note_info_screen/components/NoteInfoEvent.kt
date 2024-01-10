package com.example.appcash.view.notes.note_info_screen.components

import com.example.appcash.utils.events.Event

sealed class NoteInfoEvent: Event {

    data class InputTitleEvent(
        val title: String
    ): NoteInfoEvent()

    data class InputContentEvent(
        val content: String
    ): NoteInfoEvent()

    data class SaveNoteEvent(
        val title: String,
        val content: String,
    ): NoteInfoEvent()
}