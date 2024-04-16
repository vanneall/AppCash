package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.GetAllFinancesUseCase
import ru.point.domain.finance.separate

class GetAllFinancesUseCaseImpl(
    private val repository: FinancesRepository
) : GetAllFinancesUseCase {
    override fun invoke(): Flow<List<FinanceSeparatorDto>> {
        return repository.getAllFinances().map { list ->
            separate(list = list)
        }
    }
}