package com.example.appcash.domain.financial_transactions.implementations

import android.util.Log
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByYearMonthUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.YearMonth
import javax.inject.Inject

class GetTransactionsByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetTransactionsByYearMonthUseCase {
    override fun invoke(
        yearMonth: YearMonth,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<Finance, Category>> {
        return try {
            val monthId = yearMonth.toString()
            repository.getFinancesByMonthId(monthId)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection month exception", ex.stackTraceToString())
            emptyFlow()
        }
    }
}