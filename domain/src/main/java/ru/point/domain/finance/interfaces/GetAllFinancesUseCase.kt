package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.vo.FinanceSubset

interface GetAllFinancesUseCase {
    fun invoke(): Flow<List<FinanceSubset>>
}