package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.subset.FinanceCategorySubset
import java.time.LocalDate

interface GetFinancesByMonthUseCase {
    fun invoke(date: LocalDate, isIncome: Boolean): Flow<List<FinanceCategorySubset>>
}