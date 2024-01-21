package com.example.appcash.view.finance.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcash.view.finance.components.FinanceViewModel

@Composable
fun FinanceScreen(vm: FinanceViewModel = viewModel()) {
    Finance(
        vm.state.collectAsState().value,
        vm::handle
    )
}