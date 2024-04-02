package com.example.appcash.view.notes.info.components

data class NoteInfoState(
    val folderId: Long? = null,
    val title: String = "",
    val content: String = "",
    val isError: Boolean = false
)
