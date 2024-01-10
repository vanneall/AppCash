package com.example.appcash.view.notes.notes_folders_screen.components

import com.example.appcash.utils.events.Event

sealed class FolderListEvent : Event {
    data class CreateFolderEvent(
        val name: String
    ) : FolderListEvent()
}

