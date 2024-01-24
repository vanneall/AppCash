package com.example.appcash.view.notes.notes_folders_screen.components

import com.example.appcash.data.entities.Folder

data class MainNotesState (
    val query: String = "",
    val list: List<Folder> = emptyList(),
    val error: String? = null
)
