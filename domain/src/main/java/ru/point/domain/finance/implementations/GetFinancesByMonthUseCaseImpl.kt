package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.domain.finance.interfaces.GetFinancesByMonthUseCase
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class GetFinancesByMonthUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByMonthUseCase {
    override fun invoke(
        date: LocalDate,
    ): Flow<List<FinanceCategorySubset>> {
        val startMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        val endMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return repository.getFinancesByFolderId(startMonth.toString(), endMonth.toString())
    }
}