package com.example.appcash.view.finance.chart.components

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.models.PieChartData
import ru.point.data.data.vo.FinanceCategorySubset
import ru.point.data.data.vo.FinanceSubset
import java.time.LocalDate

data class ChartState(
    val yearMonth: LocalDate = LocalDate.now(),
    val categories: List<FinanceCategorySubset> = emptyList(),
    val categoriesForChart: List<PieChartData.Slice> = listOf(
        PieChartData.Slice(
            label = "",
            value = 1f,
            color = Color.Gray
        )
    ),
    val transactionsByYearMonth: List<FinanceSubset> = emptyList(),
    val isError: Boolean = false
)
