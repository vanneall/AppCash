@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appcash.view.tasks.main.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.appcash.view.notes.notefolders.screen.CategoryListItem
import com.example.appcash.view.popup.create.CreateCategoryPopup
import com.example.appcash.view.popup.create.CreateCategoryPopupEvent
import com.example.appcash.view.tasks.list.components.TasksSelection
import com.example.appcash.view.tasks.main.components.TasksMainState
import com.example.appcash.view.tasks.main.components.TasksMainViewModel
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun TasksMainScreen(
    viewModel: TasksMainViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.task_screen),
        navigationIcon = {
            IconButton(
                onClick = { navigateTo(Destinations.MAIN_SETTINGS_SCREEN) },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_icon),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    )

    fabState.value = FabState { viewModel.handle(CreateCategoryPopupEvent.ShowCreatePopup) }

    TasksMain(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigate = navigateTo,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun TasksMain(
    state: TasksMainState,
    navigate: (String) -> Unit,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            InfoCheep(
                title = stringResource(id = R.string.all_tasks),
                count = state.allTasksCount,
                textColor = Color.White,
                icon = painterResource(id = R.drawable.task_alt),
                modifier = Modifier
                    .height(height = 124.dp)
                    .background(color = DarkBlue, shape = RoundedCornerShape(20.dp))
                    .clickable {
                        navigate("${Destinations.TASKS_LIST_SCREEN}/${TasksSelection.ALL.name}/0")
                    }
                    .padding(16.dp)
                    .weight(1f)
            )

            InfoCheep(
                title = stringResource(id = R.string.all_bookmarks),
                count = state.bookmarkTasksCount,
                textColor = Color.Black,
                icon = painterResource(id = R.drawable.bookmark_icon),
                modifier = Modifier
                    .height(height = 124.dp)
                    .background(color = LightGray, shape = RoundedCornerShape(20.dp))
                    .clickable {
                        navigate("${Destinations.TASKS_LIST_SCREEN}/${TasksSelection.ONLY_BOOKMARKS.name}/0")
                    }
                    .padding(16.dp)
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Header(
            name = stringResource(id = R.string.my_categories),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                items = state.categories,
                key = { item -> item.id }
            ) { item ->
                CategoryListItem(
                    text = item.name,
                    count = "",
                    icon = FolderIconMapper.mapToIcon(value = item.icon),
                    iconBackgroundColor = Color(item.color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {
                            navigate("${Destinations.TASKS_LIST_SCREEN}/${TasksSelection.ONLY_CATEGORY.name}/${item.id}")
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

    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    if (state.createCategoryPopupState.isShowed) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(CreateCategoryPopupEvent.HideCreatePopup) },
            containerColor = Color.White,
        ) {
            CreateCategoryPopup(
                state = state.createCategoryPopupState,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
private fun InfoCheep(
    title: String,
    count: String,
    textColor: Color,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = if (textColor == Color.White) Color.White else DarkBlue
            )

            Text(
                text = count,
                fontSize = 28.sp,
                color = textColor,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = title,
            fontSize = 20.sp,
            color = textColor,
            fontWeight = FontWeight.Normal
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun InfoCheepPreview() {
    InfoCheep(
        title = "Задачи",
        count = "21",
        textColor = Color.White,
        icon = painterResource(id = R.drawable.task_alt),
        modifier = Modifier
            .size(174.dp, 124.dp)
            .background(color = DarkBlue, shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun InfoCheepPreviewBookmark() {
    InfoCheep(
        title = "Избранное",
        count = "8",
        textColor = Color.Black,
        icon = painterResource(id = R.drawable.bookmark_icon),
        modifier = Modifier
            .size(174.dp, 124.dp)
            .background(color = LightGray, shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    )
}
