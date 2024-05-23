package com.example.appcash.view.settings.currancy.component

import androidx.lifecycle.ViewModel
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.mode.CurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.point.data.data.datasource.settings.SettingsStore
import javax.inject.Inject

@HiltViewModel
class CurrencySelectViewModel @Inject constructor(
    private val settingsStore: SettingsStore
) : ViewModel(), EventHandler {

    private val _state = MutableStateFlow(
        listOf(
            CurrencyState(text = "Российский рубль", symbol = "₽", settingsStore.currency == "₽"),
            CurrencyState(text = "Доллар", symbol = "$", settingsStore.currency == "$"),
            CurrencyState(text = "Евро", symbol = "€", settingsStore.currency == "€"),
            CurrencyState(text = "Китайский юань", symbol = "¥", settingsStore.currency == "¥"),
        )
    )
    val state get() = _state.asStateFlow()


    override fun handle(event: Event) {
        when (event) {
            is CurrencySelectEvent.SelectCurrency -> {
                settingsStore.currency = event.symbol
                updateSelected(symbol = event.symbol)
            }
        }
    }

    private fun updateSelected(symbol: String) {
        _state.update { list -> list.map { state -> state.copy(isSelected = state.symbol == symbol) } }
    }
}