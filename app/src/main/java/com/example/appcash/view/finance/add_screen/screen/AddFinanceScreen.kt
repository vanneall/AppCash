package com.example.appcash.view.finance.add_screen.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcash.view.finance.add_screen.components.AddFinanceViewModel

@Composable
fun AddFinanceScreen(
    vm: AddFinanceViewModel,
    navigateTo: (String) -> Unit
) {
    AddFinance(
        state = vm.state.collectAsState().value,
        onEvent = vm::handle,
        navigateTo = navigateTo
    )
}