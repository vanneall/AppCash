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
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.domain.finance.implementations.FinanceSeparatorDto
import ru.point.domain.finance.implementations.GetExpenseFinanceUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesByMonthUseCaseImpl
import ru.point.domain.finance.implementations.GetIncomeFinancesByYearMonthUseCaseImpl
import java.time.LocalDate


class ChartScreenViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val openMode: OpenMode,
    private val getIncomeFinancesByYearMonthUseCase: Lazy<GetIncomeFinancesByYearMonthUseCaseImpl>,
    private val getExpenseFinanceUseCaseImpl: Lazy<GetExpenseFinanceUseCaseImpl>,
    private val getCategoryFinancesByMonthUseCase: GetFinancesByMonthUseCaseImpl
) : ViewModel(), EventHandler {

    private var _date = MutableStateFlow(LocalDate.now())

    private val _transactions = initializeFinancesByMonth()

    private val _categories = initializeCategoryFinancesByMonth()

    private val _error = MutableStateFlow(false)

    val state = combine(
        _transactions,
        _categories,
        _error,
        _date,
    ) { transaction, categories, isError, date ->
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
            isError = isError
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
                updateDate(event.newMonthIndex.toLong())
            }
        }
    }

    private fun updateDate(monthsSwitched: Long) {
        _date.update { date ->
            if (monthsSwitched > 0L) {
                date.minusMonths(monthsSwitched)
            } else {
                date.plusDays(monthsSwitched)
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
        return getCategoryFinancesByMonthUseCase
            .invoke(_date.value)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }
}