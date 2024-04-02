package com.example.appcash.view.finance.main_screen.components.components

import ru.point.data.data.entities.Category

data class AddFinanceState(
    val price: String = "",
    val query: String = "",
    val categories: List<Category> = emptyList(),
    val isError: Boolean = false
)
