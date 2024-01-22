package com.example.appcash.view.finance.add_folder.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcash.view.finance.add_folder.components.AddFolderViewModel

@Composable
fun AddFolderScreen(vm: AddFolderViewModel = viewModel()){
    AddFolder(
        vm.state.collectAsState().value,
        vm::handle
    )
}