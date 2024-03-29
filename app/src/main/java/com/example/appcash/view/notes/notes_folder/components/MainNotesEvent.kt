package com.example.appcash.view.notes.notes_folder.components

import com.example.appcash.utils.events.Event

sealed class MainNotesEvent : Event {
    data class UpsertFolderEvent(
        val name: String,
        val colorIndex: Int
    ) : MainNotesEvent()
}

