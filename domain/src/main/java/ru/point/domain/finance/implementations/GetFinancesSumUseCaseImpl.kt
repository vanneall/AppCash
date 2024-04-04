package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.GetFinancesSumUseCase

class GetFinancesSumUseCaseImpl(
    private val repository: FinancesRepository
) : GetFinancesSumUseCase {
    override fun invoke(): Flow<Int?> {
        return repository.getFinancesSum()
    }
}