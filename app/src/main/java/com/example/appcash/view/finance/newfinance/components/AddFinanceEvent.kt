package com.example.appcash.view.finance.newfinance.components

import com.example.appcash.utils.events.Event

sealed class AddFinanceEvent: Event {
    data class InputPriceEvent(
        val price: String
    ): AddFinanceEvent()

    object CreateTransactionEvent: AddFinanceEvent()

    data class SelectCategoryEvent(
        val id: Long,
    ) : AddFinanceEvent()

    data class SortCategoryEvent(
        val text: String,
    ) : AddFinanceEvent()

    object SelectIncomeButton: AddFinanceEvent()
    object SelectExpenseButton: AddFinanceEvent()
}