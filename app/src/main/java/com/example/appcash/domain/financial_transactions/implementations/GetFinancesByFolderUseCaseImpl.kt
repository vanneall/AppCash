package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.domain.financial_transactions.interfaces.GetFinancesByFolderUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.YearMonth
import javax.inject.Inject

class GetFinancesByFolderUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByFolderUseCase {
    override fun invoke(
        yearMonth: YearMonth,
    ): Flow<Map<FinanceCategoryVO, Int>> {
        val monthId = yearMonth.toString()
        val foldersFlow = repository.getFinancesByFolderId(monthId)

        return foldersFlow.map {
            it.mapKeys { map ->
                FinanceCategoryVO(
                    name = map.key.name,
                    color = map.key.colorIndex
                )
            }
        }
    }
}