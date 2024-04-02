package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.vo.FinanceCategoryVO
import java.time.YearMonth

interface GetFinancesByFolderUseCase {
    fun invoke(yearMonth: YearMonth): Flow<Map<FinanceCategoryVO, Int>>
}