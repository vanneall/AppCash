package com.example.appcash.view.finance.main.components

import ru.point.data.data.entity.subset.FinanceSubset

data class FinanceMainState(
    val sum: Int = 0,
    val transactionsByYearMonth: List<FinanceSubset> = emptyList(),
)