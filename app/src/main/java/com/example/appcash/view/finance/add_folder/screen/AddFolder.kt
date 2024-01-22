package com.example.appcash.view.finance.add_folder.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.utils.FoldersIconStore
import com.example.appcash.utils.events.Event
import com.example.appcash.view.finance.add_folder.components.AddFolderEvent
import com.example.appcash.view.finance.add_folder.components.AddFolderState

@Composable
fun AddFolder(
    state: AddFolderState,
    onEvent: (Event) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        TextField(
            value = state.name,
            onValueChange = {
                onEvent(AddFolderEvent.InputNameEvent(it))
            },
            textStyle = TextStyle(fontSize = 16.sp),
            placeholder = { Text(text = "Введите название категории", fontSize = 16.sp) },
            trailingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = null) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(70.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(vertical = 50.dp)
        ) {
            items(items = FoldersIconStore.icons) { id ->
                Icon(
                    painter = painterResource(
                        id = LocalContext.current.resources.getIdentifier(
                            id,
                            "drawable",
                            LocalContext.current.packageName
                        ),
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            onEvent(
                                AddFolderEvent.CreateFolderEvent(id)
                            )
                        }
                )
            }
        }
    }

}