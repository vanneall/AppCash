package com.example.appcash.view.notes.notes_folder.components

import com.example.appcash.data.entities.Category

data class MainNotesState (
    val query: String = "",
    val foldersList: List<Category> = emptyList(),
    val error: Boolean = true,
    val isShow: Boolean = false
)
