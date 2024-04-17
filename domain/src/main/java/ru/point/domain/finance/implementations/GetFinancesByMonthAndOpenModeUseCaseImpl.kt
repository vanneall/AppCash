package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.domain.finance.interfaces.GetFinancesByMonthUseCase
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class GetFinancesByMonthAndOpenModeUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByMonthUseCase {
    override fun invoke(
        date: LocalDate,
        isIncome: Boolean
    ): Flow<List<FinanceCategorySubset>> {
        val startMonth = date.with(TemporalAdjusters.firstDayOfMonth())
        val endMonth = date.with(TemporalAdjusters.lastDayOfMonth())

        return flow {
            val result = if (isIncome) {
                repository.getIncomeFinancesByFolderId(
                    startMonth.toString(),
                    endMonth.toString()
                )
            } else {
                repository.getExpenseFinancesByFolderId(
                    startMonth.toString(),
                    endMonth.toString()
                )
            }
            result.collect { list ->
                emit(list)
            }
        }
    }
}