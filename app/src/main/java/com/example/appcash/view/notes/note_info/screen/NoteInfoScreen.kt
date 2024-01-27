package com.example.appcash.view.notes.note_info.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.general.ErrorScreen
import com.example.appcash.view.notes.note_info.components.NoteInfoEvent
import com.example.appcash.view.notes.note_info.components.NoteInfoViewModel

@Composable
fun NoteInfoScreen(
    viewModel: NoteInfoViewModel,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "",
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                    viewModel.handle(NoteInfoEvent.SaveNoteEvent)
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navigateBack()
                    viewModel.handle(NoteInfoEvent.DeleteNoteEvent)

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    )

    when (viewModel.state.collectAsState().value.isError) {
        false -> NoteInfo(
            state = viewModel.state.collectAsState().value,
            onEvent = viewModel::handle,
            onNavigateBack = navigateBack,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        true -> ErrorScreen()
    }

}