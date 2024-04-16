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
    val isError: Boolean = false,
    val categoriesForChart: List<PieChartData.Slice> = listOf(
        PieChartData.Slice(
            label = "",
            value = 1f,
            color = Color.Transparent
        )
    ),
)
