package com.example.appcash.view.finance.chart.components

enum class OpenMode {
    EXPENSE,
    INCOME;

    companion object {
        fun handle(mode: String?): OpenMode {
            return when (mode) {
                EXPENSE.name -> EXPENSE
                INCOME.name -> INCOME
                else -> INCOME
            }
        }
    }
}