package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.vo.FinanceSubset
import java.time.LocalDate

interface GetFinancesByYearMonthUseCase {
    fun invoke(date: LocalDate): Flow<List<FinanceSubset>>
}