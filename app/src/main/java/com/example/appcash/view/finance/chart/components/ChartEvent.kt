package com.example.appcash.view.finance.chart.components

import com.example.appcash.utils.events.Event

sealed class ChartEvent: Event {
    data class InputNameEvent(
        val name: String
    ): ChartEvent()

    data class CreateFolderEvent(
        val iconId: String
    ): ChartEvent()
}