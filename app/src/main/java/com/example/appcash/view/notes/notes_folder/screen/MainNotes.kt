@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.appcash.view.notes.notes_folder.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.list.ItemListView
import com.example.appcash.view.general.other.FolderSettingsModalBottomSheet
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import com.example.appcash.view.notes.notes_folder.components.MainNotesState

@Composable
fun MainNotes(
    state: MainNotesState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isOpenState = remember {
        mutableStateOf(false)
    }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        item {
            Header(
                name = stringResource(id = R.string.my_folders)
            )
        }

        item {
            SearchTextField(
                searchQuery = state.query,
                onEvent = onEvent
            )
        }

        item {
            Spacer(
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

        item {
            ItemListView(
                name = stringResource(id = R.string.new_folder),
                icon = painterResource(id = R.drawable.create_new_folder_icon),
                backgroundIconColor = Color.Gray,
                onClick = { isOpenState.value = true }
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

        items(
            items = state.list
        ) { folderDto ->
            ItemListView(
                name = folderDto.name,
                icon = painterResource(id = R.drawable.folder_icon),
                backgroundIconColor = folderDto.color,
                onClick = { navigateTo("$NOTES_LIST_SCREEN/${FolderOpenMode.DEFINED.name}/${folderDto.id}") }
            )
        }
    }

    if (isOpenState.value) {
        FolderSettingsModalBottomSheet(
            sheetState = modalBottomSheetState,
            isOpenState = isOpenState,
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth()
        )
    }
}