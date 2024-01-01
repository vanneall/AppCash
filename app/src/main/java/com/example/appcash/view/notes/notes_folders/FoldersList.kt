package com.example.appcash.view.notes.notes_folders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.notes.TopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoldersList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        stickyHeader { TopBar() }
        item { Header("Мои папки") }
        repeat(20) {
            item { ListItem() }
        }
    }
}




@Composable
fun FolderIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .background(color = Color.Blue, shape = CircleShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.folder_icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun ListItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        FolderIcon()
        Text(
            text = "Дом",
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewFoldersList() {
    FoldersList()
}