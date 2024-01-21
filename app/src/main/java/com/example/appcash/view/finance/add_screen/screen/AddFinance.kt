package com.example.appcash.view.finance.add_screen.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TabRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.view.finance.add_screen.components.AddFinanceEvent
import com.example.appcash.view.finance.add_screen.components.AddFinanceState
import com.example.appcash.view.general.list.ItemListView
import com.example.appcash.view.general.other.SearchTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddFinance(
    state: AddFinanceState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabData = listOf(
        "Расходы",
        "Доходы"
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        2
    }
    val tabIndex = pagerState.currentPage

    Column {
        TabRow(
            selectedTabIndex = tabIndex
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    CoroutineScope(Dispatchers.Default).launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(text = text)
                })
            }
        }
        HorizontalPager(
            state = pagerState,
        ) { index ->
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {

                }
                item { Spacer(modifier = Modifier.height(50.dp)) }
                item { PriceInput(state.price, onEvent, modifier = Modifier.width(300.dp)) }
                item { Spacer(modifier = Modifier.height(50.dp)) }
                item { SearchTextField(searchQuery = state.query, onEvent = onEvent) }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item {
                    ItemListView(
                        name = "Добавьте свою категорию",
                        icon = painterResource(id = R.drawable.add_task_icon),
                        backgroundIconColor = Color.Transparent,
                        iconColor = Color.Black,
                        onClick = {}
                    )
                }
                items(items = state.folders) {
                    ItemListView(
                        name = it.name,
                        icon = painterResource(id = R.drawable.folder_icon),
                        iconColor = Color.Black,
                        backgroundIconColor = Color.Transparent,
                        onClick = {
                            onEvent(AddFinanceEvent.CreateTransactionEvent(it.id, pagerState.currentPage == 0))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PriceInput(
    price: String,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = price,
        textStyle = TextStyle(textAlign = TextAlign.End),
        onValueChange = { newValue ->
            onEvent(
                AddFinanceEvent.InputPriceEvent(
                    price = newValue
                )
            )
        },
        singleLine = true,
        placeholder = {
            Text(
                text = "0",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        shape = RoundedCornerShape(corner = CornerSize(14.dp)),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier
    )
}