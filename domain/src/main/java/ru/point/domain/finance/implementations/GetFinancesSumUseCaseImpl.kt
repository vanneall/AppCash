package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.GetFinancesSumUseCase

class GetFinancesSumUseCaseImpl(
    private val repository: FinancesRepository
) : GetFinancesSumUseCase {
    override fun invoke(): Flow<Int?> {
        return flow {
            repository.getFinancesSum()
                .collect { sum ->
                    emit(sum)
                }
        }
    }
}