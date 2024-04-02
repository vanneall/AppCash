package com.example.appcash.view.finance.add_screen.screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.main_screen.components.components.AddFinanceViewModel
import com.example.appcash.view.general.ErrorScreen

@Composable
fun FinanceAccountingScreen(
    viewModel: AddFinanceViewModel,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Учет финансов",
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )

    when (viewModel.state.collectAsState().value.isError) {
        false -> AddFinance(
            state = viewModel.state.collectAsState().value,
            onEvent = viewModel::handle,
            navigateTo = navigateTo
        )

        true -> ErrorScreen()
    }
}