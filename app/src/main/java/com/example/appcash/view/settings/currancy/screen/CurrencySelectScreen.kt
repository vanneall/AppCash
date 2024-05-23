package com.example.appcash.view.settings.currancy.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.mode.CurrencyState
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.settings.currancy.component.CurrencySelectEvent
import com.example.appcash.view.settings.currancy.component.CurrencySelectViewModel
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun CurrencySelectScreenController(
    viewModel: CurrencySelectViewModel,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>,
    modifier: Modifier = Modifier
) {

    topAppBarState.value = TopAppBarState(
        title = stringResource(R.string.currency_screen),
        navigationIcon = {
            IconButton(
                onClick = { navigateBack() },
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

    CurrencySelectScreen(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = modifier.padding(top = 52.dp)
    )
}

@Composable
private fun CurrencySelectScreen(
    state: List<CurrencyState>,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .selectableGroup()
            .padding(horizontal = 16.dp)
    ) {
        items(items = state) { currency ->
            CurrencyRadioButton(
                text = currency.text,
                currencySymbol = currency.symbol,
                radioButtonState = currency.isSelected,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CurrencyRadioButton(
    text: String,
    currencySymbol: String,
    radioButtonState: Boolean,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CurrencyIcon(
            currencyText = text,
            currencySymbol = currencySymbol
        )

        RadioButton(
            selected = radioButtonState,
            colors =  RadioButtonDefaults.colors(selectedColor = DarkBlue),
            onClick = {
                onEvent(
                    CurrencySelectEvent.SelectCurrency(currencySymbol)
                )
            },
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
private fun CurrencyIcon(
    currencyText: String,
    currencySymbol: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size = 40.dp)
                .background(
                    color = DarkBlue,
                    shape = CircleShape
                )
        ) {
            Text(
                text = currencySymbol,
                fontSize = 24.sp,
                color = Color.White
            )
        }


        Text(
            text = currencyText,
            fontSize = 18.sp,
        )
    }
}


