package com.example.appcash.view.notes.note_info.components

data class NoteInfoState(
    val folderId: Long = 0,
    val title: String = "",
    val content: String = "",
    val isError: Boolean = false
)
