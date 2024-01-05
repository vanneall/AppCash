package com.example.appcash.view.notes.notes_list.components

import com.example.appcash.data.entities.Note

data class NotesListState(
    val folderName: String = "",
    val notes: List<Note> = emptyList()
)
