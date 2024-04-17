package com.example.appcash.view.finance.chart.components

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.finance.main.components.FinanceEvent
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.domain.finance.implementations.FinanceSeparatorDto
import ru.point.domain.finance.implementations.GetExpenseFinanceUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesByMonthAndOpenModeUseCaseImpl
import ru.point.domain.finance.implementations.GetIncomeFinancesByYearMonthUseCaseImpl
import java.time.LocalDate


class ChartScreenViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val openMode: OpenMode,
    private val getIncomeFinancesByYearMonthUseCase: Lazy<GetIncomeFinancesByYearMonthUseCaseImpl>,
    private val getExpenseFinanceUseCaseImpl: Lazy<GetExpenseFinanceUseCaseImpl>,
    private val getCategoryFinancesByMonthAndOpenModeUseCase: GetFinancesByMonthAndOpenModeUseCaseImpl
) : ViewModel(), EventHandler {

    private var _date = MutableStateFlow(LocalDate.now())

    private val _transactions = MutableStateFlow<List<FinanceSeparatorDto>>(emptyList())

    private val _categories = MutableStateFlow<List<FinanceCategorySubset>>(emptyList())

    init {
        viewModelScope.launch {
            initializeCategoryFinancesByMonth().collect {
                _categories.value = it
            }
        }
        viewModelScope.launch {
            initializeFinancesByMonth().collect {
                _transactions.value = it
            }
        }
    }

    val state = combine(
        _transactions,
        _categories,
        _date,
    ) { transaction, categories, date ->
        ChartState(
            transactionsByYearMonth = transaction,
            categories = categories,
            categoriesForChart = categories.map { mapFinanceCategorySubset(it) }
                .takeIf { it.isNotEmpty() } ?: listOf(
                PieChartData.Slice(
                    label = "",
                    value = 1f,
                    color = Color.Gray
                )
            ),
            selectedDate = date,
            isIncome = openMode == OpenMode.INCOME
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        ChartState()
    )

    private fun mapFinanceCategorySubset(vo: FinanceCategorySubset): PieChartData.Slice {
        return PieChartData.Slice(
            label = vo.name ?: "",
            value = vo.sum?.toFloat() ?: 0f,
            color = Color(vo.color ?: 0x00000)
        )
    }

    override fun handle(event: Event) {
        when (event) {
            is FinanceEvent.SwitchEvent -> {
                updateDate(event.newMonthIndex)
            }
        }
    }

    private fun updateDate(monthsSwitched: Int) {
        _date.update { state.value.availableLocalDate[monthsSwitched] }
        viewModelScope.launch {
            initializeCategoryFinancesByMonth().collect {
                _categories.value = it
            }
        }

        viewModelScope.launch {
            initializeFinancesByMonth().collect {
                _transactions.value = it
            }
        }
    }

    private fun initializeFinancesByMonth(
        date: LocalDate = _date.value
    ): Flow<List<FinanceSeparatorDto>> {
        return when (openMode) {
            OpenMode.EXPENSE -> {
                getExpenseFinanceUseCaseImpl.get().invoke(date)
            }

            OpenMode.INCOME -> {
                getIncomeFinancesByYearMonthUseCase.get().invoke(date)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializeCategoryFinancesByMonth(): Flow<List<FinanceCategorySubset>> {
        return getCategoryFinancesByMonthAndOpenModeUseCase
            .invoke(_date.value, openMode == OpenMode.INCOME)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }
}