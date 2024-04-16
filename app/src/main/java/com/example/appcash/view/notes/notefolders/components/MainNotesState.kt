package com.example.appcash.view.notes.notefolders.components

import com.example.appcash.view.popup.create.CreateCategoryPopupState
import ru.point.data.data.entity.entities.Category

data class MainNotesState(
    val notesCount: String = "",
    val categoryList: List<Category> = emptyList(),
    val popupState: CreateCategoryPopupState = CreateCategoryPopupState()
)
