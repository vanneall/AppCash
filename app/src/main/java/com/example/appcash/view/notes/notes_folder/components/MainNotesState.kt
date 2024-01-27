package com.example.appcash.view.notes.notes_folder.components

import com.example.appcash.data.dto.FolderDto

data class MainNotesState (
    val query: String = "",
    val list: List<FolderDto> = emptyList(),
    val error: String? = null
)
