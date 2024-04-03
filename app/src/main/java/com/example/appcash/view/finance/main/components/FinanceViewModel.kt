package com.example.appcash.view.finance.main.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ru.point.domain.finance.interfaces.GetAllFinancesUseCase
import ru.point.domain.finance.interfaces.GetFinancesSumUseCase
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    getAllFinancesUseCase: GetAllFinancesUseCase,
    getFinancesSumUseCase: GetFinancesSumUseCase
) : ViewModel(), EventHandler {

    private val _transactions = getAllFinancesUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

    private val _sum = getFinancesSumUseCase
        .invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    val state = combine(
        _transactions,
        _sum
    ) { finance, sum ->
        FinanceMainState(
            transactionsByYearMonth = finance,
            sum = sum
        )
    }

    override fun handle(event: Event) {

    }
}