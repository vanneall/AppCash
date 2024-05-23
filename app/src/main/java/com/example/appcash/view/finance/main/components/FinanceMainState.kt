package com.example.appcash.view.finance.main.components

import ru.point.domain.finance.implementations.FinanceSeparatorDto

data class FinanceMainState(
    val sum: Int = 0,
    val transactionsByYearMonth: List<FinanceSeparatorDto> = emptyList(),
    val currency: String = ""
)