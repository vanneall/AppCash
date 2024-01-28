package com.example.appcash.domain.financial_transactions.implementations

import android.util.Log
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class InsertFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
) : InsertFinanceUseCase {
    override fun invoke(
        value: FinancialTransaction,
        id: Long,
        onError: (Event.ErrorEvent) -> Unit
    ) {
        try {
            repository.insertTransaction(value, id)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)
            Log.e("Insert finance exception", ex.stackTraceToString())
        }
    }
}