package com.example.appcash.view.notes.notes_folders

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NotesFoldersScreen() {
    FoldersList(modifier = Modifier.padding(horizontal = 20.dp))
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotesFoldersScreen() {
    NotesFoldersScreen()
}
