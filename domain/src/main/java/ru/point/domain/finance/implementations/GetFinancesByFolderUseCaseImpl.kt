package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.data.data.vo.FinanceCategoryVO
import ru.point.domain.finance.interfaces.GetFinancesByFolderUseCase
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
                    color = map.key.color
                )
            }
        }
    }
}