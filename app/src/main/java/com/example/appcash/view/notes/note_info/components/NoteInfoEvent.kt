package com.example.appcash.view.notes.note_info.components

import com.example.appcash.view.Event

sealed class NoteInfoEvent: Event {

    data class InputTitleEvent(
        val title: String
    ): NoteInfoEvent()

    data class InputContentEvent(
        val content: String
    ): NoteInfoEvent()
}