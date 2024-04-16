package com.example.appcash.view.notes.notes.components

import ru.point.data.data.entity.entities.Note

data class NotesListState(
    val categoryId: Long? = null,
    val categoryName: String = "",
    val notesList: List<Note> = emptyList(),
)
