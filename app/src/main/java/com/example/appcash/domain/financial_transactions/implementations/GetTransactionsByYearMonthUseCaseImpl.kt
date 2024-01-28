package com.example.appcash.domain.financial_transactions.implementations

import android.util.Log
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.vo.IconFolderVO
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByYearMonthUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.YearMonth
import javax.inject.Inject

class GetTransactionsByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
) : GetTransactionsByYearMonthUseCase {
    override fun invoke(
        yearMonth: YearMonth,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<FinancialTransaction, IconFolderVO>> {
        return try {
            val monthId = yearMonth.toString()
            repository.getTransactionsByMonthId(monthId)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection month exception", ex.stackTraceToString())
            emptyFlow()
        }
    }
}