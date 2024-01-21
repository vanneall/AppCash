package com.example.appcash.view.finance.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.navigation.Destinations
import com.example.appcash.utils.events.Event
import com.example.appcash.view.finance.components.FinanceEvent
import com.example.appcash.view.finance.components.FinanceState
import com.example.appcash.view.notes.note_info_screen.components.NoteOpenMode
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Finance(
    state: FinanceState,
    onEvent: (Event) -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = {
            12
        },
        initialPage = 6
    )
    pagerState.initialPage
    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HorizontalPager(
                    state = pagerState
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Chart(
                            list = when {
                                state.categoriesForChart.isEmpty() -> {
                                    listOf(
                                        PieChartData.Slice(
                                            label = "",
                                            value = 1f,
                                            color = Color.Transparent
                                        )
                                    )
                                }

                                else -> state.categoriesForChart
                            }
                        )
                        Text(
                            text = state.yearMonth.month.getDisplayName(
                                TextStyle.FULL,
                                Locale.getDefault()
                            )
                        )
                    }
                }
            }
            item { CategoryGrid(state.categories.toList()) }
            item { Spacer(modifier = Modifier.height(40.dp)) }
            items(items = state.transactionsByYearMonth.toList()) {
                TransactionRow(it, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        FloatingActionButton(
            onClick = {  },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 70.dp)
                .size(70.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onEvent(
                FinanceEvent.SwitchEvent(
                    6 - page
                )
            )
        }
    }
}

@Composable
fun TransactionRow(
    vo: Pair<FinancialTransaction, Folder>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = vo.second.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            fontSize = 16.sp,
            text = vo.first.price.toString(),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CategoryGrid(
    list: List<Pair<FinanceCategoryVO, Int>>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.height(100.dp)
    ) {
        items(
            items = list,
        ) {
            GridCell(it.first, it.second, modifier = Modifier.width(100.dp))
        }

    }
}

@Composable
fun GridCell(
    vo: FinanceCategoryVO,
    price: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 5.dp, vertical = 5.dp)
    ) {
        Text(
            text = vo.name,
            color = Color.White
        )
        Text(
            text = price.toString(),
            color = Color.White
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chart(
    list: List<PieChartData.Slice>
) {
    val donutChartData = PieChartData(
        slices = list,
        plotType = PlotType.Donut
    )

    val donutChartConfig = PieChartConfig(
        strokeWidth = 80f,
        activeSliceAlpha = .9f
    )
    DonutPieChart(
        modifier = Modifier
            .width(250.dp)
            .height(250.dp)
            .padding(20.dp),
        donutChartData,
        donutChartConfig
    )
}