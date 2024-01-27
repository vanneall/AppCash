package com.example.appcash.view.notes.notes_list.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.general.ErrorScreen
import com.example.appcash.view.notes.notes_list.components.NoteListEvent.DeleteFolder
import com.example.appcash.view.notes.notes_list.components.NoteListEvent.ShowEdit
import com.example.appcash.view.notes.notes_list.components.NotesListViewModel

@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Заметки",
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
        },
        actions = {
            IconButton(onClick = {
                viewModel.handle(ShowEdit)
            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                navigateBack()
                viewModel.handle(DeleteFolder)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    )

    when (viewModel.state.collectAsState().value.error) {
        false -> NotesList(
            state = viewModel.state.collectAsState().value,
            onEvent = viewModel::handle,
            navigateTo = navigateTo,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        true -> ErrorScreen()
    }
}