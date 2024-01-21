package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByFolderUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.YearMonth
import javax.inject.Inject

class GetTransactionsByFolderUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
): GetTransactionsByFolderUseCase {
    override fun invoke(yearMonth: YearMonth): Flow<Map<FinanceCategoryVO, Int>> {
        val monthId = yearMonth.toString()
        val foldersFlow = repository.getTransactionByFolders(monthId)
        return foldersFlow.map {
            it.mapKeys { map ->
                FinanceCategoryVO(
                    name = map.key.name,
                    color = map.key.color
                )
            }
        }
    }
}