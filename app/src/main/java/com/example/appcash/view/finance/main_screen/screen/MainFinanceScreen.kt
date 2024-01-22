package com.example.appcash.view.finance.main_screen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.finance.main_screen.components.FinanceViewModel

@Composable
fun MainFinanceScreen(
    viewModel: FinanceViewModel,
    navigateTo: (String) -> Unit
) {
    Finance(
        viewModel.state.collectAsState().value,
        viewModel::handle,
        navigateTo
    )
}