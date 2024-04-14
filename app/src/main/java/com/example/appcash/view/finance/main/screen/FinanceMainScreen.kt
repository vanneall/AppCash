package com.example.appcash.view.finance.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations
import com.example.appcash.navigation.Destinations.FINANCE_ADD_SCREEN
import com.example.appcash.utils.FolderIconMapper
import com.example.appcash.utils.events.Event
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.general.FinanceRow
import com.example.appcash.view.finance.main.components.FinanceMainState
import com.example.appcash.view.finance.main.components.FinanceViewModel
import ru.point.data.data.entities.FolderIcon

@Composable
fun MainFinanceScreen(
    viewModel: FinanceViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.finance_screen),
    )

    fabState.value = FabState { navigateTo(FINANCE_ADD_SCREEN) }

    MainMorda(
        viewModel.state.collectAsState(FinanceMainState()).value, viewModel::handle, navigateTo,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun MainMorda(
    state: FinanceMainState,
    onEvent: (Event) -> Unit,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {

        item {
            Text(
                text = state.sum.toString(),
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }


        item {
            NavigateAddButton(
                onNavigate = {
                    navigate(Destinations.FINANCE_CHART_SCREEN)
                }, modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        items(
            items = state.transactionsByYearMonth
        ) { financeSubset ->
            FinanceRow(
                icon = FolderIconMapper.mapToIcon(value = financeSubset.icon ?: FolderIcon.UNKNOWN),
                financeSubset = financeSubset
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NavigateAddButton(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = { onNavigate() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.finance_expenses),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        OutlinedButton(
            onClick = { onNavigate() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.finance_income),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NavigateAddButtonPreview() {
    NavigateAddButton(
        {}, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 10.dp)
    )
}

