package com.example.appcash.view.notes.notefolders.components

import ru.point.data.data.entities.Category

data class MainNotesState(
    val notesCount: String = "",
    val foldersList: List<Category> = emptyList(),
    val isCreatePopupShowed: Boolean = false,
    val popupState: CreatePopupState = CreatePopupState()
)
