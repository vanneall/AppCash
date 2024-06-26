package com.example.appcash.view.notes.info.components

import com.example.appcash.utils.events.Event

sealed class NoteInfoEvent : Event {

    data class InputTitleEvent(val title: String) : NoteInfoEvent()

    data class InputContentEvent(val content: String) : NoteInfoEvent()

    object SaveNoteEvent : NoteInfoEvent()
}