package com.example.appcash.domain.financial_transactions.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByFolderUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import java.time.YearMonth
import javax.inject.Inject

class GetTransactionsByFolderUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
) : GetTransactionsByFolderUseCase {
    override fun invoke(
        yearMonth: YearMonth,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<FinanceCategoryVO, Int>> {
        return try {
            val monthId = yearMonth.toString()
            val foldersFlow = repository.getTransactionByFolders(monthId)
            foldersFlow.map {
                it.mapKeys { map ->
                    FinanceCategoryVO(
                        name = map.key.name,
                        color = 1L
                    )
                }
            }
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection transaction exception", ex.stackTraceToString())
            emptyFlow()
        }
    }
}