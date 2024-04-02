package com.example.appcash.view.notes.notes.components

import ru.point.data.data.entities.Note

data class NotesListState(
    val folderId: Long? = null,
    val folderName: String = "",
    val notesList: List<Note> = emptyList(),
)
