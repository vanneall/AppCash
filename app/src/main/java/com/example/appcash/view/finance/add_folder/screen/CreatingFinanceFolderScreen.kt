package com.example.appcash.view.finance.add_folder.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.appcash.view.finance.add_folder.components.AddFolderViewModel

@Composable
fun CreatingFinanceFolderScreen(viewModel: AddFolderViewModel){
    AddFolder(
        viewModel.state.collectAsState().value,
        viewModel::handle
    )
}