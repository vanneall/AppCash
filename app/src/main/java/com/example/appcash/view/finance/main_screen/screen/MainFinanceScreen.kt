package com.example.appcash.view.finance.main_screen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.main_screen.components.FinanceViewModel
import com.example.appcash.view.general.ErrorScreen

@Composable
fun MainFinanceScreen(
    viewModel: FinanceViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Финансы",
    )

    when (viewModel.state.collectAsState().value.isError) {
        false -> Finance(
            viewModel.state.collectAsState().value,
            viewModel::handle,
            navigateTo
        )

        true -> ErrorScreen()
    }
}