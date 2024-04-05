package com.example.appcash.view.finance.newfinance.components

import com.example.appcash.view.popup.create.CreateCategoryPopupState
import ru.point.data.data.entities.Category

data class AddFinanceState(
    val price: String = "",
    val selectedCategoryId: Long? = null,
    val isIncomeButtonSelected: Boolean = false,
    val createCategoryPopupState: CreateCategoryPopupState = CreateCategoryPopupState(),
    val categories: List<Category> = emptyList(),
)
