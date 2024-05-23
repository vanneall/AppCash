package com.example.appcash.view.settings.currancy.component

import com.example.appcash.utils.events.Event

sealed class CurrencySelectEvent: Event {

    data class SelectCurrency(val symbol: String): CurrencySelectEvent()

}