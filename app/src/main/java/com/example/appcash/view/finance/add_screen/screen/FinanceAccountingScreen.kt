package com.example.appcash.view.finance.add_screen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.finance.add_screen.components.AddFinanceViewModel

@Composable
fun FinanceAccountingScreen(
    viewModel: AddFinanceViewModel,
    navigateTo: (String) -> Unit
) {
    AddFinance(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigateTo = navigateTo
    )
}