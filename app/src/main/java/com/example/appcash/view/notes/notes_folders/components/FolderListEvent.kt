package com.example.appcash.view.notes.notes_folders.components

import com.example.appcash.view.Event

sealed class FolderListEvent : Event {

    data class CreateFolderEvent(
        val name: String
    ) : FolderListEvent()

    data class OpenFolderEvent(
        val folderId: Long
    ) : FolderListEvent()
}

