package com.example.appcash.view.notes.note_info

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcash.view.notes.note_info.components.NoteInfoViewModelFactoryProvider
import com.example.appcash.view.notes.notes_list.components.NoteListViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors

@Composable
fun NoteInfoScreen() {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        NoteInfoViewModelFactoryProvider::class.java
    ).provideNoteInfoViewModelFactory()
    val vm = viewModel { factory.create(1) }
    NoteInfoField(
        state = vm.state.collectAsState().value,
        onEvent = vm::handle,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewNoteInfoScreen() {
    NoteInfoScreen()
}