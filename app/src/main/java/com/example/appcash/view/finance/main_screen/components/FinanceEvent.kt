package com.example.appcash.view.finance.main_screen.components

import com.example.appcash.utils.events.Event

sealed class FinanceEvent: Event {
    data class SwitchEvent(
        val isNextMonth: Int
    ): FinanceEvent() {
        enum class MonthQueueType {
            NEXT,
            CURRENT,
            PREVIOUS
        }
    }
}