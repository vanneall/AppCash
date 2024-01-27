package com.example.appcash.view.finance.main_screen.components

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.data.vo.IconFolderVO
import java.time.YearMonth

data class FinanceState(
    val yearMonth: YearMonth = YearMonth.now(),
    val categories: Map<FinanceCategoryVO, Int> = emptyMap(),
    val categoriesForChart: List<PieChartData.Slice> = listOf(
        PieChartData.Slice(
            label = "",
            value = 1f,
            color = Color.Transparent
        )
    ),
    val transactionsByYearMonth: Map<FinancialTransaction, IconFolderVO> = emptyMap()
)