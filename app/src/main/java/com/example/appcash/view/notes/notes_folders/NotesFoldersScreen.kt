package com.example.appcash.view.notes.notes_folders

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appcash.view.notes.notes_folders.components.FoldersListViewModel

@Composable
fun NotesFoldersScreen(
    viewModel: FoldersListViewModel,
    navigateTo: (String) -> Unit
) {
    FoldersListView(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigateTo = navigateTo,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotesFoldersScreen() {
    //NotesFoldersScreen()
}
