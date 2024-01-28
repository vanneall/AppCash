package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.utils.events.Event

interface InsertFinanceUseCase {

    operator fun invoke(value: FinancialTransaction, id: Long, onError: (Event.ErrorEvent) -> Unit)

}