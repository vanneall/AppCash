package com.example.appcash.view.finance.chart.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastSumBy
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.appcash.R
import com.example.appcash.utils.FolderIconMapper
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.mode.convertToString
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.chart.components.ChartScreenViewModel
import com.example.appcash.view.finance.chart.components.ChartState
import com.example.appcash.view.finance.general.FinanceRow
import com.example.appcash.view.finance.main.components.FinanceEvent
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray
import com.example.appcash.view.ui.theme.Turquoise
import com.kizitonwose.calendar.core.yearMonth
import ru.point.data.data.entity.entities.FolderIcon

@Composable
fun FinanceChartScreen(
    viewModel: ChartScreenViewModel,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.finance_screen),
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                },
                modifier = Modifier
                    .size(36.dp)
                    .background(color = LightGray, shape = CircleShape)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    )

    fabState.value = FabState { }

    FinanceChart(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FinanceChart(
    state: ChartState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        pageCount = {
            state.availableLocalDate.size
        },
        initialPage = state.availableLocalDate.size - 1
    )
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        state = gridState,
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = state.categories.fastSumBy { item -> item.sum ?: 0 }.toString() + "  ${state.currency}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 30.sp
                )

                Text(
                    text = (if (state.isIncome) "Доходы за " else "Расходы за ") + state.selectedDate.yearMonth,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }

        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .background(color = Color.Transparent)
            ) {
                Chart(
                    list = state.categoriesForChart,
                    modifier = Modifier
                        .background(Color.White)
                        .size(250.dp)
                        .padding(20.dp),
                )
            }
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Spacer(modifier = Modifier.height(1.dp))
        }

        items(
            items = state.categories
        ) { categorySubset ->
            ChartCheep(
                name = categorySubset.name ?: "",
                price = categorySubset.sum.toString() + " ${state.currency}",
                color = Color(categorySubset.color ?: 0x000000),
                icon = FolderIconMapper.mapToIcon(
                    value = categorySubset.icon ?: FolderIcon.UNKNOWN
                )
            )
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Spacer(modifier = Modifier.height(40.dp))
        }

        state.transactionsByYearMonth.forEach { dto ->
            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Text(
                    text = convertToString(dto.separator),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            }

            items(
                span = { GridItemSpan(maxCurrentLineSpan) },
                items = dto.list
            ) { financeSubset ->
                FinanceRow(
                    financeSubset = financeSubset,
                    icon = FolderIconMapper.mapToIcon(
                        value = financeSubset.icon ?: FolderIcon.UNKNOWN
                    ),
                    currency = state.currency
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { monthIndex ->
            onEvent(
                FinanceEvent.SwitchEvent(
                    newMonthIndex = monthIndex
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
    icon: Painter,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(28.dp)
            .wrapContentWidth()
            .background(color = LightGray, shape = RoundedCornerShape(20.dp))
            .padding(start = 2.dp, top = 2.dp, bottom = 2.dp, end = 8.dp)
    ) {
        Icon(
            painter = icon,
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
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.wrapContentWidth()
        )
        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = price,
            color = Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Preview
@Composable
fun GridCellPreview() {
    ChartCheep("Одежда", "12 000Р", Turquoise, painterResource(id = R.drawable.task_alt))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Chart(
    list: List<PieChartData.Slice>,
    modifier: Modifier = Modifier
) {
    val donutChartData = PieChartData(
        slices = list,
        plotType = PlotType.Donut
    )

    val donutChartConfig = PieChartConfig(

        strokeWidth = 80f,
        activeSliceAlpha = 1f,
        isAnimationEnable = false,
        isClickOnSliceEnabled = false
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        DonutPieChart(
            modifier = modifier.background(color = Color.Transparent),
            donutChartData,
            donutChartConfig
        )
    }
}


@Composable
@Preview(showBackground = false)
private fun ChartPreview() {
    Chart(
        listOf(PieChartData.Slice("label", 1f, Color.Gray))
    )
}