package com.example.appcash.view.finance.add_folder.screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.add_folder.components.AddFolderViewModel

@Composable
fun CreatingFinanceFolderScreen(
    viewModel: AddFolderViewModel,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Добавить папку",
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

    AddFolder(
        viewModel.state.collectAsState().value,
        viewModel::handle
    )
}