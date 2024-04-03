package com.example.appcash.view.finance.chart.components

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.finance.main.components.FinanceEvent
import com.example.appcash.view.ui.theme.DarkBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.data.data.vo.FinanceCategorySubset
import ru.point.data.data.vo.FinanceSubset
import ru.point.domain.finance.interfaces.GetFinancesByFolderUseCase
import ru.point.domain.finance.interfaces.GetFinancesByYearMonthUseCase
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class ChartScreenViewModel @Inject constructor(
    private val getFinancesByYearMonthUseCase: GetFinancesByYearMonthUseCase,
    private val getFinancesByFolderUseCase: GetFinancesByFolderUseCase
) : ViewModel(), EventHandler {

    private var _todayDate = LocalDate.now()

    private val _transactions = MutableStateFlow<List<FinanceSubset>>(emptyList())

    private val _categories = MutableStateFlow<List<FinanceCategorySubset>>(emptyList())

    private val _error = MutableStateFlow(false)

    init {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                getFinancesByFolderUseCase.invoke(_todayDate).collect {
                    _categories.value = it
                }
            }
            viewModelScope.launch(Dispatchers.IO) {
                getFinancesByYearMonthUseCase.invoke(_todayDate).collect {
                    _transactions.value = it
                }
            }
        } catch (exception: Exception) {
            updateError()
        }
    }

    val state = combine(
        _transactions,
        _categories,
        _error
    ) { transaction, categories, isError ->
        ChartState(
            transactionsByYearMonth = transaction,
            categories = categories,
            categoriesForChart = categories.map { mapFinanceCategoryVO(it) }
                .takeIf { it.isNotEmpty() } ?: listOf(
                PieChartData.Slice(
                    label = "",
                    value = 1f,
                    color = Color.Gray
                )
            ),
            isError = isError
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),
        ChartState()
    )

    private fun mapFinanceCategoryVO(vo: FinanceCategorySubset): PieChartData.Slice {
        return PieChartData.Slice(
            label = vo.name ?: "",
            value = vo.sum?.toFloat() ?: 0f,
            color = DarkBlue
        )
    }

    override fun handle(event: Event) {
        try {
            when (event) {
                is FinanceEvent.SwitchEvent -> {
                    switchMonth(event.newMonthIndex.toLong())
                }
            }
        } catch (exception: Exception) {
            updateError()
        }
    }

    private fun switchMonth(newMonthIndex: Long) {
        when (newMonthIndex) {
            (0).toLong() -> {
                _todayDate = LocalDate.now()
                actualizeTransactions()
                actualizeCategories()
            }

            in 1..6 -> {
                _todayDate =
                    LocalDate.now().minusMonths(abs(newMonthIndex))
                actualizeTransactions()
                actualizeCategories()

            }

            else -> viewModelScope.launch(Dispatchers.IO) {
                _todayDate =
                    LocalDate.now().plusMonths(abs(newMonthIndex))
                actualizeTransactions()
                actualizeCategories()

            }
        }
    }

    private fun actualizeTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            getFinancesByFolderUseCase.invoke(_todayDate).collect {
                _categories.value = it
            }
        }
    }

    private fun actualizeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getFinancesByYearMonthUseCase.invoke(_todayDate).collect {
                _transactions.value = it
            }
        }
    }

    private fun updateError() {
        _error.update { true }
    }
}