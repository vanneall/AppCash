package com.example.appcash.view.finance.newfinance.components

import com.example.appcash.utils.events.Event

sealed class AddFinanceEvent: Event {
    data class InputPriceEvent(
        val price: String
    ): AddFinanceEvent()

    data class CreateTransactionEvent(
        val id: Long,
    ) : AddFinanceEvent()

    object SelectIncomeButton: AddFinanceEvent()
    object SelectExpenseButton: AddFinanceEvent()
}