package com.example.appcash.view.finance.newfinance.components

import ru.point.data.data.entities.Category

data class AddFinanceState(
    val price: String = "",
    val isIncomeButtonSelected: Boolean = false,
    val categories: List<Category> = emptyList(),
)
