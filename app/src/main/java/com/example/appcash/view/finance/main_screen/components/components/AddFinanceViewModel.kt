package com.example.appcash.view.finance.main_screen.components.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import ru.point.domain.finance.interfaces.InsertFinanceUseCase
import ru.point.domain.notes.interfaces.GetCategoryByTypeUseCase
import java.time.YearMonth
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class AddFinanceViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    private val insertFinanceUseCase: InsertFinanceUseCase,
) : ViewModel(), EventHandler {

    private val _list =
        getCategoryByTypeUseCase.invoke(type = Category.Discriminator.FINANCES)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _searchQuery = MutableStateFlow("")

    private val _price = MutableStateFlow("")

    private val _error = MutableStateFlow(false)

    val state = combine(
        _list,
        _searchQuery,
        _price,
        _error
    ) { list, query, price, error ->
        AddFinanceState(
            price = price,
            query = query,
            categories = list.filter { it.name.contains(query) },
            isError = error
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AddFinanceState())

    override fun handle(event: Event) {
        try {
            when (event) {
                is SearchEvent -> {
                    updateQuery(query = event.query)
                }

                is AddFinanceEvent.InputPriceEvent -> {
                    updatePrice(price = event.price)
                }

                is AddFinanceEvent.CreateTransactionEvent -> {
                    createFinance(isMinus = event.isMinus, id = event.id)
                }

                is Event.ErrorEvent -> {
                    updateError()
                }
            }
        } catch (ex: Exception) {
            updateError()
        }

    }

    private fun updateQuery(query: String) {
        _searchQuery.update { query }
    }

    private fun updatePrice(price: String) {
        _price.update { price }
    }

    private fun updateError() {
        _error.update { true }
    }

    private fun createFinance(isMinus: Boolean, id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFinanceUseCase(
                value = Finance(
                    price = if (isMinus) -1 * abs(_price.value.toInt()) else abs(
                        _price.value.toInt()
                    ),
                    folderId = id,
                    date = YearMonth.now()
                )
            )
        }
    }
}