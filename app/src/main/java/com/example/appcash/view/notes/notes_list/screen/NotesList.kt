package com.example.appcash.view.notes.notes_list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.navigation.Destinations.NOTE_SCREEN
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.notes.note_info_screen.components.NoteOpenMode
import com.example.appcash.view.notes.notes_list.components.NotesListState

@Composable
fun NotesList(
    state: NotesListState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = modifier
        ) {
            item { Header(state.folderName) }
            item { SearchTextField(state.searchQuery, onEvent) }
            item { Spacer(modifier = Modifier.padding(bottom = 10.dp)) }
            itemsIndexed(state.notes) { index, item ->
                ListItem(
                    title = item.title,
                    content = item.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigateTo("$NOTE_SCREEN/${NoteOpenMode.EDIT.name}/${item.id}/${state.folderId}") }
                )
                if (index < state.notes.size - 1)
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(top = 15.dp)
                    )
            }
        }
        FloatingActionButton(
            onClick = { navigateTo("$NOTE_SCREEN/${NoteOpenMode.CREATE.name}/${-1}/${state.folderId}") },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 70.dp)
                .size(70.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
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