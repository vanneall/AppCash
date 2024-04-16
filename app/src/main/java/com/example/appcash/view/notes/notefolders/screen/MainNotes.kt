package com.example.appcash.view.notes.notefolders.screen

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations
import com.example.appcash.utils.FolderIconMapper
import com.example.appcash.utils.events.Event
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.notes.notefolders.components.MainNotesState
import com.example.appcash.view.notes.notefolders.components.MainNotesViewModel
import com.example.appcash.view.popup.create.CreateCategoryPopup
import com.example.appcash.view.popup.create.CreateCategoryPopupEvent
import com.example.appcash.view.ui.theme.Blue
import com.example.appcash.view.ui.theme.DarkTurquoise
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.FolderIcon

@Composable
fun MainNotesScreen(
    viewModel: MainNotesViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.note_screen)
    )

    fabState.value = FabState {
        viewModel.handle(CreateCategoryPopupEvent.ShowCreatePopup)
    }

    MainNotes(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigateTo = navigateTo,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainNotes(
    state: MainNotesState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Header(
            name = stringResource(id = R.string.my_categories),
            modifier = Modifier.padding(vertical = 15.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CategoryListItem(
                    text = stringResource(id = R.string.all_notes),
                    count = state.notesCount,
                    icon = painterResource(id = R.drawable.note_nav_icon),
                    iconBackgroundColor = DarkTurquoise,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {
                            navigateTo("${Destinations.NOTES_LIST_SCREEN}/0")
                        }
                        .background(
                            color = LightGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 12.dp)
                )
            }



            items(
                items = state.categoryList,
                key = { item -> item.id }
            ) { item ->
                CategoryListItem(
                    text = item.name,
                    count = "недоступно",
                    icon = FolderIconMapper.mapToIcon(value = item.icon),
                    iconBackgroundColor = Color(item.color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {
                            navigateTo("${Destinations.NOTES_LIST_SCREEN}/${item.id}")
                        }
                        .background(
                            color = LightGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 12.dp)
                )
            }
        }
    }

    if (state.popupState.isShowed) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(CreateCategoryPopupEvent.HideCreatePopup) },
            containerColor = Color.White,
            modifier = Modifier
                .height(350.dp)
        ) {
            CreateCategoryPopup(
                state = state.popupState,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
fun CategoryListItem(
    text: String,
    icon: Painter,
    iconBackgroundColor: Color,
    count: String,
    modifier: Modifier = Modifier,
    isArrowVisible: Boolean = true,
    iconShape: Shape = RoundedCornerShape(10.dp),
    iconColor: Color = Color.White,
    color: Color = Color.Black
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            tint = iconColor,
            contentDescription = "Иконка фолдера",
            modifier = Modifier
                .size(size = 40.dp)
                .background(
                    color = iconBackgroundColor,
                    shape = iconShape
                )
                .padding(all = 8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            color = color,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(weight = 1f)
        )

        Text(
            text = count,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = Gray,
            modifier = Modifier.height(18.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        if (isArrowVisible) {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Иконка продолжения",
                modifier = Modifier.size(size = 18.dp),
                tint = Gray
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Превью карточки фолдера"
)
@Composable
private fun CategoryListItemPreview() {
    CategoryListItem(
        text = "Дом",
        count = "2",
        icon = painterResource(id = R.drawable.task_alt),
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
            categoryList = listOf(
                Category(
                    name = "Дом",
                    color = 0,
                    discriminator = Category.Discriminator.Note,
                    icon = FolderIcon.SERVICES,
                ),
                Category(
                    name = "Работа",
                    color = 0,
                    discriminator = Category.Discriminator.Note,
                    icon = FolderIcon.SERVICES,
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