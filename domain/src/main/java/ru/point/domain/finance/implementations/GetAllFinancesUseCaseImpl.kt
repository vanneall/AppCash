package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.data.data.vo.FinanceSubset
import ru.point.domain.finance.interfaces.GetAllFinancesUseCase

class GetAllFinancesUseCaseImpl(
    private val repository: FinancesRepository
) : GetAllFinancesUseCase {
    override fun invoke(): Flow<List<FinanceSubset>> {
        return repository.getAllFinances()
    }
}