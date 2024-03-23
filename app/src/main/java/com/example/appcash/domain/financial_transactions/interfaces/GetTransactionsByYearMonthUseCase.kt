package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.Finance
import com.example.appcash.data.entities.Category
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface GetTransactionsByYearMonthUseCase {
    fun invoke(
        yearMonth: YearMonth,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<Finance, Category>>
}