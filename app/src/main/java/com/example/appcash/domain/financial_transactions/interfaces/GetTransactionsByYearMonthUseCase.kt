package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.vo.IconFolderVO
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface GetTransactionsByYearMonthUseCase {

    fun invoke(
        yearMonth: YearMonth,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<FinancialTransaction, IconFolderVO>>

}