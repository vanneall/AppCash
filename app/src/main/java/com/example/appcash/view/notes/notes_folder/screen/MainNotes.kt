package com.example.appcash.view.notes.notes_folder.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.data.entities.Category
import com.example.appcash.navigation.Destinations.NOTES_LIST_SCREEN
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import com.example.appcash.view.notes.notes_folder.components.MainNotesState
import com.example.appcash.view.ui.theme.Blue
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun MainNotes(
    state: MainNotesState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Header(
            name = stringResource(id = R.string.my_categories),
            modifier = Modifier.padding(vertical = 15.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            items(
                items = state.foldersList,
                key = { item -> item.id }
            ) { item ->
                CategoryListItem(
                    name = item.name,
                    countOfInnerItems = "2",
                    icon = Icons.Default.Home,
                    iconBackgroundColor = Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(
                            color = LightGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 12.dp)
                        .clickable { navigateTo("$NOTES_LIST_SCREEN/${item.id}/${FolderOpenMode.DEFINED.name}") }
                )
            }
        }
    }
}

@Composable
fun CategoryListItem(
    name: String,
    countOfInnerItems: String,
    icon: ImageVector,
    iconBackgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint = Color.White,
            contentDescription = "Иконка фолдера",
            modifier = Modifier
                .size(size = 40.dp)
                .background(
                    color = iconBackgroundColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(weight = 1f)
        )

        Text(
            text = countOfInnerItems,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Gray,
            modifier = Modifier.height(18.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "Иконка продолжения",
            modifier = Modifier.size(size = 18.dp),
            tint = Gray
        )
    }
}

@Preview(
    showBackground = true,
    name = "Превью карточки фолдера"
)
@Composable
private fun CategoryListItemPreview() {
    CategoryListItem(
        name = "Дом",
        countOfInnerItems = "2",
        icon = Icons.Default.Home,
        iconBackgroundColor = Blue,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = LightGray,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ScreenPreview() {
    MainNotes(
        state = MainNotesState(
            foldersList = listOf(
                Category(
                    name = "Дом",
                    colorIndex = 0,
                    discriminator = Category.Discriminator.NOTES,
                    icon = "wewe"
                ),
                Category(
                    name = "Работа",
                    colorIndex = 0,
                    discriminator = Category.Discriminator.NOTES,
                    icon = "wewe"
                )
            )
        ),
        onEvent = {},
        navigateTo = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.White)
    )
}