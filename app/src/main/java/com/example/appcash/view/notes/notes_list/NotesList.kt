package com.example.appcash.view.notes.notes_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations.NOTE_INFO_SCREEN
import com.example.appcash.view.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.notes.TopBar
import com.example.appcash.view.notes.note_info_screen.components.NoteOpenOpenMode
import com.example.appcash.view.notes.notes_list.components.NoteListEvent
import com.example.appcash.view.notes.notes_list.components.NotesListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesList(
    state: NotesListState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = modifier
        ) {
            stickyHeader { TopBar() }
            item { Header(state.folderName) }
            item { SearchTextField(onEvent) }
            item { Spacer(modifier = Modifier.padding(bottom = 10.dp)) }
            itemsIndexed(state.notes) { index, item ->
                ListItem(
                    title = item.title,
                    content = item.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigateTo("$NOTE_INFO_SCREEN/${NoteOpenOpenMode.EDIT.name}/${item.id}/${state.folderId}") }
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
            onClick = { navigateTo("$NOTE_INFO_SCREEN/${NoteOpenOpenMode.CREATE.name}/${-1}/${state.folderId}") },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    onEvent: (Event) -> Unit,
) {
    val searchQuery = remember { mutableStateOf("") }

    val isDeleteIconEnabled = remember {
        derivedStateOf { searchQuery.value.isNotEmpty() }
    }
    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = {
            searchQuery.value = it
            onEvent(
                NoteListEvent.SearchNoteEvent(
                    searchQuery = searchQuery.value
                )
            )
        },
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.search)) },
        shape = RoundedCornerShape(corner = CornerSize(14.dp)),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isDeleteIconEnabled.value) Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable {
                    searchQuery.value = ""
                    onEvent(
                        NoteListEvent.SearchNoteEvent(
                            searchQuery = searchQuery.value
                        )
                    )
                }
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onEvent(
                NoteListEvent.SearchNoteEvent(
                    searchQuery = searchQuery.value
                )
            )
        }),
        modifier = Modifier.fillMaxWidth()
    )
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

@Preview(showBackground = true)
@Composable
private fun PreviewNotesList() {
    //NotesList(modifier = Modifier.padding(horizontal = 20.dp))
}