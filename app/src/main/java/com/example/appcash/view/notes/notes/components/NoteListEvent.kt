package com.example.appcash.view.notes.notes.components

import com.example.appcash.utils.events.Event

sealed class NoteListEvent : Event {
    object DeleteFolder : NoteListEvent()

    object ShowEdit : NoteListEvent()
}
