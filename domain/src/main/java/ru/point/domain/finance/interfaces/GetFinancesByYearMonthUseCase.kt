package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import java.time.YearMonth

interface GetFinancesByYearMonthUseCase {
    fun invoke(yearMonth: YearMonth): Flow<Map<Finance, Category>>
}