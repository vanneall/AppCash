package com.example.appcash.domain.financial_transactions.implementations

import android.util.Log
import com.example.appcash.data.entities.Finance
import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class InsertFinanceUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : InsertFinanceUseCase {
    override fun invoke(
        value: Finance,
        onError: (Event.ErrorEvent) -> Unit
    ) {
        try {
            repository.insertFinance(value)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)
            Log.e("Insert finance exception", ex.stackTraceToString())
        }
    }
}