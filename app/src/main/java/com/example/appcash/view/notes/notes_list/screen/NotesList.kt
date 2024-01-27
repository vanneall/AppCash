@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appcash.view.notes.notes_list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.navigation.Destinations.NOTE_SCREEN
import com.example.appcash.utils.OpenParamsMagicNumbers.OPEN_FOR_CREATE
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.other.FolderSettingsModalBottomSheet
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.notes.note_info_screen.components.NoteOpenMode.CREATE
import com.example.appcash.view.notes.note_info_screen.components.NoteOpenMode.EDIT
import com.example.appcash.view.notes.notes_list.components.NotesListState

@Composable
fun NotesList(
    state: NotesListState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = modifier
        ) {
            item {
                Header(
                    name = state.folderName,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }

            item {
                SearchTextField(
                    searchQuery = state.searchQuery,
                    onEvent = onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
            }

            itemsIndexed(
                items = state.notesList
            ) { index, note ->
                ListItem(
                    title = note.title,
                    content = note.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigateTo("$NOTE_SCREEN/${state.folderId}/${note.id}/${EDIT.name}") }
                )

                Divider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 15.dp)
                ).takeIf { index < state.notesList.size - 1 }
            }
        }

        FloatingActionButton(
            onClick = { navigateTo("$NOTE_SCREEN/${state.folderId}/$OPEN_FOR_CREATE}/${CREATE.name}") },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 70.dp)
                .size(70.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        if (state.showEdit) {
            FolderSettingsModalBottomSheet(
                sheetState = sheetState,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ListItem(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 17.sp,
            color = Color.Black
        )
        Text(
            text = content,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}