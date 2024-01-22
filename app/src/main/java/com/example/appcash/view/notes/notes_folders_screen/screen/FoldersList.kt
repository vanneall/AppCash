package com.example.appcash.view.notes.notes_folders_screen.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations.NOTES_LIST_SCREEN
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.CreateFolderDialogView
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.list.ItemListView
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.general.other.TopBar
import com.example.appcash.view.notes.notes_folders_screen.components.FolderListState
import com.example.appcash.view.notes.notes_folders_screen.components.FolderOpenMode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoldersList(
    state: FolderListState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isDialogOpen = remember {
        mutableStateOf(false)
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        stickyHeader { TopBar() }
        item { Header(name = stringResource(id = R.string.my_folders)) }
        item { SearchTextField(state.searchQuery, onEvent) }
        item { Spacer(modifier = Modifier.padding(bottom = 10.dp)) }
        item {
            ItemListView(
                name = stringResource(id = R.string.new_folder),
                icon = painterResource(id = R.drawable.create_new_folder_icon),
                backgroundIconColor = Color.Gray,
                onClick = { isDialogOpen.value = true }
            )
        }
        item {
            ItemListView(
                name = stringResource(id = R.string.all_notes),
                icon = painterResource(id = R.drawable.kid_star),
                backgroundIconColor = Color(0xFFE9AD14),
                onClick = { navigateTo("$NOTES_LIST_SCREEN/${FolderOpenMode.ALL.name}/${0}") }
            )
        }
        items(items = state.folders) { folder ->
            ItemListView(
                name = folder.name,
                icon = painterResource(id = R.drawable.folder_icon),
                backgroundIconColor = Color.Blue,
                onClick = { navigateTo("$NOTES_LIST_SCREEN/${FolderOpenMode.DEFINED.name}/${folder.id}") }
            )
        }
    }

    if (isDialogOpen.value) {
        CreateFolderDialogView(
            onCreateEvent = onEvent,
            isDialogOpenedMutableState = isDialogOpen
        )
    }
}