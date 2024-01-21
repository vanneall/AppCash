package com.example.appcash.view.finance.add_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.FolderType
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class AddFinanceViewModel @Inject constructor(
    getFoldersUseCase: GetFoldersUseCase,
    private val insertFinanceUseCase: InsertFinanceUseCase
) : ViewModel(), EventHandler {

    private val _list = getFoldersUseCase.invoke(FolderType.FINANCIAL)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _searchQuery = MutableStateFlow("")

    private val _price = MutableStateFlow("")

    val state = combine(_list, _searchQuery, _price) { list, query, price ->
        AddFinanceState(
            price = price,
            query = query,
            folders = list.filter { it.name.contains(query) }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AddFinanceState())

    override fun handle(event: Event) {
        when (event) {
            is SearchEvent -> {
                _searchQuery.update { event.searchQuery }
            }

            is AddFinanceEvent.InputPriceEvent -> {
                _price.update { event.price }
            }

            is AddFinanceEvent.CreateTransactionEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    insertFinanceUseCase(
                        value = FinancialTransaction(
                            price = if (event.isMinus) -1 * abs(_price.value.toInt()) else abs(_price.value.toInt()),
                            date = YearMonth.now()
                        ),
                        id = event.id
                    )
                }

            }
        }
    }
}