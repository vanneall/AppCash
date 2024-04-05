package com.example.appcash.view.tasks.main.components

import com.example.appcash.view.popup.create.CreateCategoryPopupState
import ru.point.data.data.entities.Category

data class TasksMainState(
    val categories: List<Category> = emptyList(),
    val createCategoryPopupState: CreateCategoryPopupState = CreateCategoryPopupState(),
    val allTasksCount: String = "",
    val bookmarkTasksCount: String = "",
)