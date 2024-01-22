package com.example.appcash.view.notes.notes_folders_screen.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcash.view.notes.notes_folders_screen.components.FoldersListViewModel

@Composable
fun MainNotesScreen(
    viewModel: FoldersListViewModel,
    navigateTo: (String) -> Unit
) {
    FoldersList(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigateTo = navigateTo,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}
