package com.example.appcash.view.finance.add_screen.components

import com.example.appcash.data.entities.Category

data class AddFinanceState(
    val price: String = "",
    val query: String = "",
    val categories: List<Category> = emptyList(),
    val isError: Boolean = false
)
