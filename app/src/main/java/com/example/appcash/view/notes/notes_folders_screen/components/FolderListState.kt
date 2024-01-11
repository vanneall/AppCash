package com.example.appcash.view.notes.notes_folders_screen.components

import com.example.appcash.data.entities.Folder

data class FolderListState(
    val searchQuery: String = "",
    val folders: List<Folder> = emptyList()
)
