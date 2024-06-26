package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.domain.finance.implementations.FinanceSeparatorDto

interface GetAllFinancesUseCase {
    fun invoke(): Flow<List<FinanceSeparatorDto>>
}