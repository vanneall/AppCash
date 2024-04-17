package ru.point.domain.finance.implementations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.GetAllFinancesUseCase
import ru.point.domain.finance.separate

class GetAllFinancesUseCaseImpl(
    private val repository: FinancesRepository
) : GetAllFinancesUseCase {
    override fun invoke(): Flow<List<FinanceSeparatorDto>> {
        return flow {
            repository.getAllFinances()
                .map { list ->
                    withContext(Dispatchers.Default) {
                        separate(list = list)
                    }
                }
                .collect { list ->
                    emit(list)
                }
        }
    }
}