package com.example.appcash.view.finance.main_screen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.main_screen.components.FinanceViewModel

@Composable
fun MainFinanceScreen(
    viewModel: FinanceViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Финансы",
    )

    Finance(
        viewModel.state.collectAsState().value,
        viewModel::handle,
        navigateTo
    )
}