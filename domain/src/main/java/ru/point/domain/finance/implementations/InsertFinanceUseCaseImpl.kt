package ru.point.domain.finance.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.InsertFinanceUseCase
import javax.inject.Inject

class InsertFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : InsertFinanceUseCase {
    override fun invoke(value: Finance) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertFinance(value)
        }
    }
}