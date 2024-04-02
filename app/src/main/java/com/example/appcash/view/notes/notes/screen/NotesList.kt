@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appcash.view.notes.notes.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.navigation.Destinations.NOTE_SCREEN
import com.example.appcash.utils.events.Event
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.notes.notes.components.NotesListState
import com.example.appcash.view.notes.notes.components.NotesListViewModel
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    val state = viewModel.state.collectAsState().value
    topAppBarState.value = TopAppBarState(
        title = state.folderName,
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                }) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
        }
    )
    fabState.value = FabState { navigateTo("$NOTE_SCREEN/${state.folderId ?: 0}/0") }

    NotesList(
        state = state,
        onEvent = viewModel::handle,
        navigateTo = navigateTo,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Composable
private fun NotesList(
    state: NotesListState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = state.notesList,
            key = { item -> item.id },
        ) { item ->
            NoteListItem(
                title = item.title,
                content = item.content,
                modifier = Modifier
                    .size(width = 358.dp, height = 64.dp)
                    .background(
                        color = LightGray, shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { navigateTo("$NOTE_SCREEN/${state.folderId ?: 0}/${item.id}") }
            )
        }
    }
}

@Composable
private fun NoteListItem(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.width(286.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Gray,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Иконка больше",
            modifier = Modifier
                .size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteListPreview() {
    NoteListItem(
        title = "Разработка мобильного приложения",
        content = "Цель проекта - создание удобного и функционального приложения",
        modifier = Modifier
            .size(width = 358.dp, height = 64.dp)
            .background(
                color = LightGray, shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}