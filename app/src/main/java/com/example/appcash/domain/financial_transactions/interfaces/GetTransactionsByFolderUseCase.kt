package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface GetTransactionsByFolderUseCase {
    fun invoke(
        yearMonth: YearMonth,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<FinanceCategoryVO, Int>>
}