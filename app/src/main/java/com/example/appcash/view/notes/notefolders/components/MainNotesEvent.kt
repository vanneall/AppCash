package com.example.appcash.view.notes.notefolders.components

import com.example.appcash.utils.events.Event

sealed class MainNotesEvent : Event {
    data class InsertFolder(
        val name: String,
        val color: Int
    ) : MainNotesEvent()

    data class InputName(
        val name: String
    ) : MainNotesEvent()

    object ShowCreatePopup: MainNotesEvent()

    object HideCreatePopup: MainNotesEvent()
}

