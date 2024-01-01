package com.example.appcash.view.notes.notes_list

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.notes.TopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        stickyHeader { TopBar() }
        item { Header("Все заметки") }
        item { SearchTextField() }
        item { Spacer(modifier = Modifier.padding(bottom = 10.dp)) }
        items(20) {
            ListItem()
            Divider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(top = 15.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        singleLine = true,
        placeholder = { Text(text = "Поиск") },
        shape = RoundedCornerShape(corner = CornerSize(14.dp)),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ListItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Название",
            fontSize = 17.sp,
            color = Color.Black
        )
        Text(
            text = "Текст заметки",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotesList() {
    NotesList(modifier = Modifier.padding(horizontal = 20.dp))
}