package com.example.appcash.view.notes.notes_list.components

import com.example.appcash.data.entities.Note

data class NotesListState(
    val folderId: Long = 0,
    val searchQuery: String = "",
    val folderName: String = "",
    val notes: List<Note> = emptyList()
)
