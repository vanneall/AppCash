package com.example.appcash.view.notes.notes_list

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcash.view.notes.notes_list.components.NoteListViewModelFactoryProvider
import com.example.appcash.view.notes.notes_list.components.NotesListViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel,
    navigateBack: () -> Unit,
    navigateTo: (String) -> Unit
) {
    NotesList(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigateTo = navigateTo,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotesListScreen() {
    //NotesListScreen()
}