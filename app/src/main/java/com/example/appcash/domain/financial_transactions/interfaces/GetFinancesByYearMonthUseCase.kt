package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface GetFinancesByYearMonthUseCase {
    fun invoke(yearMonth: YearMonth): Flow<Map<Finance, Category>>
}