package com.example.appcash.view.notes.note_info_screen.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcash.view.notes.note_info_screen.components.NoteInfoViewModel

@Composable
fun NoteInfoScreen(
    viewModel: NoteInfoViewModel,
    navigateBack: () -> Unit
) {
    NoteInfo(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        onNavigateBack = navigateBack,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}