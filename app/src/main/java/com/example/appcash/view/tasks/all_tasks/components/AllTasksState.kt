package com.example.appcash.view.tasks.all_tasks.components

import com.example.appcash.view.popup.CreateCategoryPopupState
import ru.point.data.data.entities.Category

data class AllTasksState(
    val categories: List<Category> = emptyList(),
    val isConfigPopupShowed: Boolean = false,
    val createCategoryPopupState: CreateCategoryPopupState = CreateCategoryPopupState(),
    val isEditPopupShowed: Boolean = false,
    val plannedTasks: Int = 0,
    val bookmarkTasks: Int = 0,
)