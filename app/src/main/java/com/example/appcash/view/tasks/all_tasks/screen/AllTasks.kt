@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appcash.view.tasks.all_tasks.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.list.ItemListView
import com.example.appcash.view.general.list.RoundedIconView
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.general.other.FolderSettingsModalBottomSheet
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import com.example.appcash.view.tasks.all_tasks.components.AllTasksState

@Composable
fun AllTasks(
    state: AllTasksState,
    navigate: (String) -> Unit,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(bottom = 50.dp),
        modifier = modifier
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TaskCheeps(
                    title = stringResource(id = R.string.planned),
                    icon = painterResource(id = R.drawable.planned_task_icon),
                    iconBgColor = Color.Red,
                    count = state.plannedTasks.toString().takeIf { it != "0" } ?: "",
                    modifier = Modifier.weight(0.5f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                TaskCheeps(
                    title = stringResource(id = R.string.completed),
                    icon = painterResource(id = R.drawable.confirm_icon),
                    iconBgColor = Color.Green,
                    count = state.completeTasks.toString().takeIf { it != "0" } ?: "",
                    modifier = Modifier.weight(0.5f)
                )
            }
        }

        item { Header(name = stringResource(id = R.string.my_tasks_folder)) }

        item {
            SearchTextField(
                state.searchQuery,
                onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }

        item {
            ItemListView(
                name = stringResource(id = R.string.new_tasks),
                icon = painterResource(id = R.drawable.new_tasks_folder_icon),
                backgroundIconColor = Color.Gray,
                onClick = { onEvent(BottomSheetEvent.ShowEvent) }
            )
        }

        item {
            ItemListView(
                name = "Все задачи",
                icon = painterResource(id = R.drawable.kid_star),
                backgroundIconColor = Color(0xFFE9AD14),
                onClick = { navigate("${Destinations.TASKS_SCREEN}/${FolderOpenMode.ALL.name}/${0}") }
            )
        }

        items(
            items = state.folders,
            key = { dto -> dto.id }
        ) { folderDto ->
            ItemListView(
                name = folderDto.name,
                icon = painterResource(id = R.drawable.tasks_folder_icon),
                backgroundIconColor = folderDto.color,
                onClick = { navigate("${Destinations.TASKS_SCREEN}/${FolderOpenMode.DEFINED.name}/${folderDto.id}") }
            )
        }
    }

    if (state.isShowed) {
        FolderSettingsModalBottomSheet(
            sheetState = modalBottomSheetState,
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TaskCheeps(
    title: String,
    icon: Painter,
    iconBgColor: Color,
    count: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Black),
                shape = RoundedCornerShape(corner = CornerSize(size = 25.dp))
            )
            .padding(
                horizontal = 25.dp,
                vertical = 15.dp
            )
    ) {
        TaskCounterBlockInfo(
            icon = icon,
            iconBgColor = iconBgColor,
            count = count
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            color = Color.LightGray,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun TaskCounterBlockInfo(
    icon: Painter,
    iconBgColor: Color,
    count: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RoundedIconView(icon = icon, tint = Color.White, backGroundColor = iconBgColor)
        Text(
            text = count,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}