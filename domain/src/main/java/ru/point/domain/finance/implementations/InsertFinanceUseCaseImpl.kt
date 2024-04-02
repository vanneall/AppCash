package ru.point.domain.finance.implementations

import ru.point.data.data.entities.Finance
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.domain.finance.interfaces.InsertFinanceUseCase
import javax.inject.Inject

class InsertFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : InsertFinanceUseCase {
    override fun invoke(value: Finance) {
        repository.insertFinance(value)
    }
}