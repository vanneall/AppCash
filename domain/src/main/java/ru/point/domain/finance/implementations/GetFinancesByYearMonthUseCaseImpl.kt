package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.GetFinancesByYearMonthUseCase
import java.time.YearMonth
import javax.inject.Inject

class GetFinancesByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByYearMonthUseCase {
    override fun invoke(yearMonth: YearMonth): Flow<Map<Finance, Category>> {
        val monthId = yearMonth.toString()
        return repository.getFinancesByMonthId(monthId)
    }
}