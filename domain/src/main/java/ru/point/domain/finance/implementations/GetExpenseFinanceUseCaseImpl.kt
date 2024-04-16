package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.GetExpenseFinanceUseCase
import ru.point.domain.finance.separate
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class GetExpenseFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetExpenseFinanceUseCase {
    override fun invoke(todayDate: LocalDate): Flow<List<FinanceSeparatorDto>> {
        val startMonth = todayDate.with(TemporalAdjusters.firstDayOfMonth())
        val endMonth = todayDate.with(TemporalAdjusters.lastDayOfMonth())

        return repository
            .getExpenseFinancesByMonthId(startMonth.toString(), endMonth.toString())
            .map { list ->
                separate(todayDate, list)
            }
    }
}