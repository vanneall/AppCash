package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.entity.subset.FinanceSubset
import ru.point.domain.finance.interfaces.GetFinancesByYearMonthUseCase
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class GetFinancesByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByYearMonthUseCase {
    override fun invoke(date: LocalDate): Flow<List<FinanceSubset>> {
        val startMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        val endMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return repository.getFinancesByMonthId(startMonth.toString(), endMonth.toString())
    }
}