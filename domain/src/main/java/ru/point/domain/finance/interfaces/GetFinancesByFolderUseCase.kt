package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.vo.FinanceCategorySubset
import java.time.LocalDate

interface GetFinancesByFolderUseCase {
    fun invoke(date: LocalDate): Flow<List<FinanceCategorySubset>>
}