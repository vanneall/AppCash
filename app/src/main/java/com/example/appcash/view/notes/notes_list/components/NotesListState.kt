package com.example.appcash.view.notes.notes_list.components

import ru.point.data.data.entities.Note

data class NotesListState(
    val folderId: Long = 0,
    val folderName: String = "",
    val searchQuery: String = "",
    val notesList: List<Note> = emptyList(),
    val error: Boolean = false,
    val showEdit: Boolean = false
)
