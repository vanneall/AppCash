package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.domain.finance.implementations.FinanceSeparatorDto
import java.time.LocalDate

interface GetExpenseFinanceUseCase {
    fun invoke(todayDate: LocalDate): Flow<List<FinanceSeparatorDto>>
}