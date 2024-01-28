package com.example.appcash.view.finance.add_screen.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations.CREATING_FINANCE_FOLDER_SCREEN
import com.example.appcash.utils.events.Event
import com.example.appcash.view.finance.add_screen.components.AddFinanceEvent
import com.example.appcash.view.finance.add_screen.components.AddFinanceState
import com.example.appcash.view.general.list.ItemListView
import com.example.appcash.view.general.other.SearchTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddFinance(
    state: AddFinanceState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabData = listOf(
        "Расходы",
        "Доходы"
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        tabData.size
    }
    val tabIndex = remember { mutableIntStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = tabIndex.intValue,
            backgroundColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(
                    selected = tabIndex.intValue == index,
                    onClick = {
                        tabIndex.intValue = index
                    },
                    text = {
                        Text(text = text)
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    PriceInput(
                        price = state.price,
                        onEvent = onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                }

                item {
                    SearchTextField(
                        searchQuery = state.query,
                        onEvent = onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp, bottom = 10.dp)
                    )
                }

                item {
                    ItemListView(
                        name = "Добавьте свою категорию",
                        icon = painterResource(id = R.drawable.add_task_icon),
                        backgroundIconColor = Color.Transparent,
                        iconColor = Color.Black,
                        onClick = { navigateTo(CREATING_FINANCE_FOLDER_SCREEN) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                items(
                    items = state.folders
                ) {
                    ItemListView(
                        name = it.folder.name,
                        icon = painterResource(
                            id = LocalContext.current.resources.getIdentifier(
                                it.iconId,
                                "drawable",
                                LocalContext.current.packageName
                            ),
                        ),
                        iconSize = 40.dp,
                        iconColor = Color.Black,
                        backgroundIconColor = Color.Transparent,
                        onClick = {
                            onEvent(
                                AddFinanceEvent.CreateTransactionEvent(
                                    it.folder.id,
                                    pagerState.currentPage == 0
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
    LaunchedEffect(tabIndex.intValue) {
        pagerState.animateScrollToPage(tabIndex.intValue)
    }

    LaunchedEffect(pagerState.currentPage) {
        tabIndex.intValue = pagerState.currentPage
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
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Decimal
        ),
        modifier = modifier
    )
}