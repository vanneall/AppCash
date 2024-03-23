package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.entities.Finance
import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import javax.inject.Inject

class InsertFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : InsertFinanceUseCase {
    override fun invoke(value: Finance) {
        throw Exception()
        repository.insertFinance(value)
    }
}