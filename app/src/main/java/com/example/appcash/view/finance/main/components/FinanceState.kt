package com.example.appcash.view.finance.main.components

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.models.PieChartData
import ru.point.data.data.entities.Finance
import ru.point.data.data.entities.Category
import ru.point.data.data.vo.FinanceCategoryVO
import java.time.YearMonth

data class FinanceState(
    val yearMonth: YearMonth = YearMonth.now(),
    val categories: Map<FinanceCategoryVO, Int> = emptyMap(),
    val categoriesForChart: List<PieChartData.Slice> = listOf(
        PieChartData.Slice(
            label = "",
            value = 1f,
            color = Color.Gray
        )
    ),
    val transactionsByYearMonth: Map<Finance, Category> = emptyMap(),
    val isError: Boolean = false
)