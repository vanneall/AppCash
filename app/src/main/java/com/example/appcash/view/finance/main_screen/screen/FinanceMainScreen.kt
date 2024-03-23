package com.example.appcash.view.finance.main_screen.screen

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.data.entities.Finance
import com.example.appcash.data.entities.Category
import com.example.appcash.data.vo.FinanceCategoryVO
import com.example.appcash.navigation.Destinations.FINANCE_ACCOUNTING_SCREEN
import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.ParamsStore.getSafety
import com.example.appcash.utils.events.Event
import com.example.appcash.view.finance.main_screen.components.FinanceEvent
import com.example.appcash.view.finance.main_screen.components.FinanceState
import java.util.Locale

const val CURRENT_MONTH_INDEX = 11

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Finance(
    state: FinanceState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = {
            12
        },
        initialPage = 11
    )
    val gridState = rememberLazyGridState()

    Box {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            state = gridState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                HorizontalPager(
                    state = pagerState,
                ) {
                    Chart(
                        text = state.yearMonth.month.getDisplayName(
                            java.time.format.TextStyle.FULL_STANDALONE,
                            Locale.getDefault()
                        ).capitalize(),
                        textStyle = TextStyle(
                            fontSize = 16.sp
                        ),
                        list = state.categoriesForChart,
                        modifier = Modifier
                            .size(250.dp)
                            .padding(20.dp),
                    )
                }
            }
            items(
                items = state.categories.toList(),
            ) {
                GridCell(
                    it.first,
                    it.second,
                    modifier = Modifier
                        .padding(20.dp)
                        .height(30.dp)
                )
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Spacer(modifier = Modifier.height(40.dp))
            }

            items(
                span = { GridItemSpan(maxCurrentLineSpan) },
                items = state.transactionsByYearMonth.toList()
            ) {
                TransactionRow(
                    it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 20.dp)
                )
            }
        }
        FloatingActionButton(
            onClick = { navigateTo(FINANCE_ACCOUNTING_SCREEN) },
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
        snapshotFlow { pagerState.currentPage }.collect { monthIndex ->
            onEvent(
                FinanceEvent.SwitchEvent(
                    newMonthIndex = CURRENT_MONTH_INDEX - monthIndex
                )
            )
        }
    }
}

@Composable
fun TransactionRow(
    vo: Pair<Finance, Category>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(
                id = LocalContext.current.resources.getIdentifier(
                    vo.second.icon,
                    "drawable",
                    LocalContext.current.packageName
                ),
            ),
            contentDescription = null
        )
        Text(
            text = vo.second.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            fontSize = 16.sp,
            text = if (vo.first.price < 0) "${vo.first.price}" else "+${vo.first.price}",
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
            color = if (vo.first.price < 0) Color.Red else Color.Green
        )
    }
}

@Composable
private fun GridCell(
    vo: FinanceCategoryVO,
    price: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(color = colorsList.getSafety(vo.color), shape = RoundedCornerShape(10.dp))
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
private fun Chart(
    text: String,
    textStyle: TextStyle,
    list: List<PieChartData.Slice>,
    modifier: Modifier = Modifier
) {
    val donutChartData = PieChartData(
        slices = list,
        plotType = PlotType.Donut
    )

    val donutChartConfig = PieChartConfig(
        strokeWidth = 80f,
        activeSliceAlpha = .9f,
        backgroundColor = Color.Transparent,
        isAnimationEnable = true,
        isClickOnSliceEnabled = false,

    )
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        DonutPieChart(
            modifier = modifier,
            donutChartData,
            donutChartConfig
        )
        Text(
            text = text,
            style = textStyle
        )
    }
}