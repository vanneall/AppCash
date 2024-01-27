package com.example.appcash.view.notes.notes_folder.components

import com.example.appcash.data.dto.FolderDto

data class MainNotesState (
    val query: String = "",
    val foldersList: List<FolderDto> = emptyList(),
    val error: Boolean = true,
    val isShow: Boolean = false
)
