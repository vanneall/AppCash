package com.example.appcash.view.notes.notes_folders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.appcash.R
import com.example.appcash.navigation.Destinations
import com.example.appcash.navigation.Destinations.FOLDER_TO_NOTE_LINK_SCREEN
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.list.ItemListView
import com.example.appcash.view.notes.TopBar
import com.example.appcash.view.notes.notes_folders.components.FolderListEvent
import com.example.appcash.view.notes.notes_folders.components.FolderListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoldersListView(
    state: FolderListState,
    onEvent: (FolderListEvent) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
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
        item {
            ItemListView(
                name = stringResource(id = R.string.new_folder),
                icon = painterResource(id = R.drawable.create_new_folder_icon),
                backgroundIconColor = Color.Gray,
                onClick = { isDialogOpen.value = true }
            )
        }
        itemsIndexed(items = state.folders) { index, folder ->
            ItemListView(
                name = folder.name,
                icon = painterResource(id = R.drawable.folder_icon),
                backgroundIconColor = Color.Blue,
                onClick = { navigateTo("$FOLDER_TO_NOTE_LINK_SCREEN/${index + 1}") }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateFolderDialogView(
    onCreateEvent: (FolderListEvent) -> Unit,
    isDialogOpenedMutableState: MutableState<Boolean>
) {
    val name = remember { mutableStateOf("") }
    Dialog(onDismissRequest = { isDialogOpenedMutableState.value = false }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(corner = CornerSize(20.dp))
                )
                .width(300.dp)
                .height(180.dp)
                .padding(15.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = name.value,
                onValueChange = { text -> name.value = text },
                label = { Text(text = stringResource(id = R.string.folder_name)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CreateCancelButtonsRowView(
                onEvent = onCreateEvent,
                isDialogOpenedMutableState = isDialogOpenedMutableState,
                name = name.value
            )
        }
    }
}


@Composable
private fun CreateCancelButtonsRowView(
    onEvent: (FolderListEvent) -> Unit,
    isDialogOpenedMutableState: MutableState<Boolean>,
    name: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { isDialogOpenedMutableState.value = false }
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
        Button(
            onClick = {
                onEvent(
                    FolderListEvent.CreateFolderEvent(
                        name = name
                    )
                )
                isDialogOpenedMutableState.value = false
            }) {
            Text(text = stringResource(id = R.string.create))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFoldersListView() {
    //FoldersList()
}