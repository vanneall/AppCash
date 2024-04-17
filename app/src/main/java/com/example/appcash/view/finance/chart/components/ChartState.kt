package com.example.appcash.view.finance.chart.components

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.models.PieChartData
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.domain.finance.implementations.FinanceSeparatorDto
import java.time.LocalDate

data class ChartState(
    val selectedDate: LocalDate = LocalDate.now(),
    val categories: List<FinanceCategorySubset> = emptyList(),
    val transactionsByYearMonth: List<FinanceSeparatorDto> = emptyList(),
    val isIncome: Boolean = true,
    val categoriesForChart: List<PieChartData.Slice> = listOf(
        PieChartData.Slice(
            label = "",
            value = 1f,
            color = Color.Transparent
        )
    ),
    val availableLocalDate: List<LocalDate> = listOf(
        LocalDate.now().minusMonths(11),
        LocalDate.now().minusMonths(10),
        LocalDate.now().minusMonths(9),
        LocalDate.now().minusMonths(8),
        LocalDate.now().minusMonths(7),
        LocalDate.now().minusMonths(6),
        LocalDate.now().minusMonths(5),
        LocalDate.now().minusMonths(4),
        LocalDate.now().minusMonths(3),
        LocalDate.now().minusMonths(2),
        LocalDate.now().minusMonths(1),
        LocalDate.now(),
    )
)
