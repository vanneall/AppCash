package com.example.appcash.view.notes.notes_folders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.data.Folder
import com.example.appcash.view.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.notes.TopBar
import com.example.appcash.view.notes.notes_folders.components.FolderListEvent
import com.example.appcash.view.notes.notes_folders.components.FolderListState
import com.example.appcash.view.notes.notes_folders.components.FoldersListViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoldersList(
    state: FolderListState,
    onEvent: (FolderListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        stickyHeader { TopBar() }
        item { Header("Мои папки") }
        item {
            ListItem(
                name = "Новая папка",
                icon = painterResource(id = R.drawable.create_new_folder_icon),
                backgroundIconColor = Color.Gray,
                onClick = { onEvent(FolderListEvent.CreateFolderEvent("Моя папка")) }
            )
        }
        items(items = state.folders) { folder ->
            ListItem(
                name = folder.name,
                icon = painterResource(id = R.drawable.folder_icon),
                backgroundIconColor = Color.Blue,
                onClick = {}
            )
        }
    }
}

@Composable
fun ListItem(
    name: String,
    icon: Painter,
    backgroundIconColor: Color,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        FolderIcon(
            icon = icon,
            tint = Color.White,
            backGroundColor = backgroundIconColor
        )
        Text(
            text = name,
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun FolderIcon(
    icon: Painter,
    tint: Color,
    backGroundColor: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .background(color = backGroundColor, shape = CircleShape)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(28.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewFoldersList() {
    //FoldersList()
}