package com.example.appcash.view.finance.add_screen.screen.components

import ru.point.data.data.entities.Category

data class AddFinanceState(
    val price: String = "",
    val isIncomeButtonSelected: Boolean = false,
    val categories: List<Category> = emptyList(),
)
