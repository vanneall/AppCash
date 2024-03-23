package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.Finance
import com.example.appcash.utils.events.Event

interface InsertFinanceUseCase {

    operator fun invoke(value: Finance, onError: (Event.ErrorEvent) -> Unit)

}