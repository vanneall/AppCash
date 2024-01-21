package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import javax.inject.Inject

class InsertFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
): InsertFinanceUseCase {
    override fun invoke(value: FinancialTransaction, id: Long) {
        repository.insertTransaction(value, id)
    }
}