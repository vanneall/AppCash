package com.example.appcash.view.finance.newfinance.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddFinanceViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    private val insertFinanceUseCase: InsertFinanceUseCase,
) : ViewModel(), EventHandler {

    private val _list =
        getCategoryByTypeUseCase.invoke(type = Category.Discriminator.FINANCES)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _price = MutableStateFlow("")

    private val _isIncomeButtonSelected = MutableStateFlow(false)


    val state = combine(
        _list,
        _price,
        _isIncomeButtonSelected
    ) { list, price, isIncome ->
        AddFinanceState(
            price = price,
            categories = list,
            isIncomeButtonSelected = isIncome
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AddFinanceState())

    override fun handle(event: Event) {
        when (event) {

            is AddFinanceEvent.InputPriceEvent -> {
                updatePrice(price = event.price)
            }

            is AddFinanceEvent.CreateTransactionEvent -> {
                createFinance(id = event.id)
            }

            is AddFinanceEvent.SelectExpenseButton -> {
                selectExpense()
            }

            is AddFinanceEvent.SelectIncomeButton -> {
                selectIncome()
            }
        }
    }


    private fun selectIncome() {
        _isIncomeButtonSelected.update { true }
    }

    private fun selectExpense() {
        _isIncomeButtonSelected.update { false }
    }

    private fun updatePrice(price: String) {
        _price.update { price }
    }


    private fun createFinance(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            insertFinanceUseCase(
                value = Finance(
                    price = _price.value.toInt(),
                    folderId = id,
                    date = LocalDate.now()
                )
            )
        }
    }
}