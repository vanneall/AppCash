package com.example.appcash.view.finance

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.main.components.FinanceEvent
import com.example.appcash.view.finance.main.components.FinanceState
import com.example.appcash.view.finance.main.components.FinanceViewModel
import com.example.appcash.view.general.ErrorScreen
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray
import com.example.appcash.view.ui.theme.Turquoise
import java.util.Locale

const val CURRENT_MONTH_INDEX = 11

@Composable
fun MainFinance(
    viewModel: FinanceViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Финансы",
    )

    when (viewModel.state.collectAsState().value.isError) {
        false -> FinanceChart(
            viewModel.state.collectAsState().value,
            viewModel::handle,
            navigateTo
        )

        true -> ErrorScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinanceChart(
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

            }
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
private fun ChartCheep(
    name: String,
    price: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = LightGray)
            .padding(start = 2.dp, top = 2.dp, bottom = 2.dp, end = 20.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.car_folder_icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .background(color = color, shape = CircleShape)
                .size(20.dp)
                .padding(1.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = name,
            color = Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = price,
            color = Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun GridCellPreview() {
    ChartCheep("Одежда", "12 000Р", Turquoise)
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