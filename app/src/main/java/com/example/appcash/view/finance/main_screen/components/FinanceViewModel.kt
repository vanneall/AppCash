package com.example.appcash.view.finance.main_screen.components

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.data.vo.IconFolderVO
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByFolderUseCase
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByYearMonthUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
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
class FinanceViewModel @Inject constructor(
    private val getTransactionsByYearMonthUseCase: GetTransactionsByYearMonthUseCase,
    private val getTransactionsByFolderUseCase: GetTransactionsByFolderUseCase
) : ViewModel(), EventHandler {

    private val _month = MutableStateFlow<YearMonth>(YearMonth.now())

    private val _transactions =
        MutableStateFlow<Map<FinancialTransaction, IconFolderVO>>(emptyMap())

    private val _categories = MutableStateFlow<Map<FinanceCategoryVO, Int>>(emptyMap())

    private val _error = MutableStateFlow(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsByFolderUseCase.invoke(_month.value, ::handle).collect {
                _categories.value = it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsByYearMonthUseCase.invoke(_month.value, ::handle).collect {
                _transactions.value = it
            }
        }
    }

    val state = combine(
        _transactions,
        _categories,
        _month,
        _error
    ) { transaction, categories, month, isError ->
        FinanceState(
            yearMonth = month,
            transactionsByYearMonth = transaction,
            categories = categories,
            categoriesForChart = categories.map { mapFinanceCategoryVO(it.key, it.value) }
                .takeIf { it.isNotEmpty() } ?: listOf(
                PieChartData.Slice(
                    label = "",
                    value = 1f,
                    color = Color.Gray
                )
            ),
            isError = isError
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), FinanceState())

    private fun mapFinanceCategoryVO(vo: FinanceCategoryVO, value: Int): PieChartData.Slice {
        return PieChartData.Slice(
            label = vo.name,
            value = value.toFloat(),
            color = Color.Blue
        )
    }

    override fun handle(event: Event) {
        when (event) {
            is FinanceEvent.SwitchEvent -> {
                switchMonth(event.newMonthIndex.toLong())
            }

            is Event.ErrorEvent -> {
                updateError()
            }
        }
    }

    private fun switchMonth(newMonthIndex: Long) {
        when (newMonthIndex) {
            (0).toLong() -> {
                _month.value = YearMonth.now()
                actualizeTransactions()
                actualizeCategories()
            }

            in 1..6 -> {
                _month.value =
                    YearMonth.now().minusMonths(abs(newMonthIndex))
                actualizeTransactions()
                actualizeCategories()

            }

            else -> viewModelScope.launch(Dispatchers.IO) {
                _month.value =
                    YearMonth.now().plusMonths(abs(newMonthIndex))
                actualizeTransactions()
                actualizeCategories()

            }
        }
    }

    private fun updateError() {
        _error.update { true }
    }

    private fun actualizeTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsByFolderUseCase.invoke(_month.value, ::handle).collect {
                _categories.value = it
            }
        }
    }

    private fun actualizeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsByYearMonthUseCase.invoke(_month.value, ::handle).collect {
                _transactions.value = it
            }
        }
    }
}