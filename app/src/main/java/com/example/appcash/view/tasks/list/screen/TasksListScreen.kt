package com.example.appcash.view.tasks.list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.popup.taskconfigurator.TaskConfiguratorPopup
import com.example.appcash.view.popup.taskconfigurator.TaskConfiguratorPopupEvent
import com.example.appcash.view.popup.taskcontroll.TaskControlPopup
import com.example.appcash.view.popup.taskcontroll.TaskControlPopupEvent
import com.example.appcash.view.tasks.list.components.TaskListEvent
import com.example.appcash.view.tasks.list.components.TasksListState
import com.example.appcash.view.tasks.list.components.TasksListViewModel
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray
import com.example.appcash.view.ui.theme.Orange
import ru.point.data.data.entity.entities.Task
import ru.point.data.data.entity.entities.TaskWithTask
import java.time.LocalDate

@Composable
fun TasksListScreen(
    viewModel: TasksListViewModel,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    val state = viewModel.state.collectAsState()

    val screenTitle = when (state.value.screenTitle) {
        TasksListViewModel.ALL -> stringResource(id = R.string.all_tasks_screen)
        TasksListViewModel.BOOKMARKS -> stringResource(id = R.string.all_bookmarks_screen)
        TasksListViewModel.ERROR -> stringResource(id = R.string.error_title_screen)
        "" -> stringResource(id = R.string.task_screen)
        else -> state.value.screenTitle
    }

    topAppBarState.value = TopAppBarState(
        title = screenTitle,
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                },
                modifier = Modifier
                    .size(36.dp)
                    .background(color = LightGray, shape = CircleShape)
                    .padding(4.dp)
            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    )

    fabState.value = FabState { viewModel.handle(TaskConfiguratorPopupEvent.ShowPopup()) }

    TasksList(
        state = state.value,
        onEvent = viewModel::handle,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TasksList(
    state: TasksListState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(
            items = state.tasks,
            key = { task -> task.id }
        ) { task ->
            TaskBlockListItem(
                task = task,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (state.taskControlPopupState.isShowed) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(TaskControlPopupEvent.HidePopup) },
            containerColor = Color.White,
            modifier = Modifier
                .height(height = 240.dp)
        ) {
            TaskControlPopup(
                state = state.taskControlPopupState,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            )
        }
    }

    if (state.taskConfiguratorPopupState.isShowed) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(TaskConfiguratorPopupEvent.HidePopup) },
            containerColor = Color.White,
            modifier = Modifier
                .fillMaxSize()
        ) {
            TaskConfiguratorPopup(
                state = state.taskConfiguratorPopupState,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun TaskListItem(
    id: Long,
    text: String,
    textFontSize: Int,
    checkboxSize: Int,
    isBookmarked: Boolean?,
    subtext: String?,
    date: String?,
    options: Boolean?,
    isCompleted: Boolean,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Checkbox(
            checked = isCompleted,
            onCheckedChange = { onEvent(TaskListEvent.UpdateCompletedState(id, it)) },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.White,
                uncheckedColor = Color.White,
                checkmarkColor = DarkBlue
            ),
            modifier = Modifier
                .size(checkboxSize.dp)
                .border(
                    width = 2.dp,
                    color = DarkBlue,
                    shape = CircleShape
                )
                .wrapContentSize()
        )

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = text,
                fontSize = textFontSize.sp,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = if (!isCompleted) Color.Black else Gray,
                textDecoration = TextDecoration.LineThrough.takeIf { isCompleted },
                modifier = Modifier.fillMaxWidth(),
            )

            subtext?.let {
                Text(
                    text = subtext,
                    fontSize = 14.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    color = Gray,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        date?.let {
            Text(
                text = date,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Gray
            )
        }

        Spacer(modifier = Modifier.width(8.dp))


        isBookmarked?.let {
            Icon(
                painter = painterResource(id = R.drawable.bookmark_icon),
                contentDescription = null,
                tint = if (isBookmarked) Orange else Color.Black,
                modifier = Modifier
                    .size(10.dp)
                    .clickable { onEvent(TaskListEvent.UpdateBookmarked(id)) }
            )
        }



        Spacer(modifier = Modifier.width(8.dp))

        options?.let {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Иконка больше",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onEvent(
                            TaskControlPopupEvent.ShowPopup(
                                id = id,
                                name = text,
                                description = subtext,
                                localDate = LocalDate.parse(date)
                            )
                        )
                    }
            )
        }
    }
}

@Composable
fun TaskBlockListItem(
    task: TaskWithTask,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TaskListItem(
            id = task.id,
            text = task.text,
            textFontSize = 16,
            subtext = task.description.takeIf { text -> text.isNotEmpty() },
            checkboxSize = 24,
            isCompleted = task.isCompleted,
            date = task.date?.toString(),
            onEvent = onEvent,
            options = true,
            isBookmarked = task.isBookmarked
        )

        Spacer(modifier = Modifier.height(20.dp))

        task.subtasks.forEach { subtask ->
            TaskListItem(
                id = subtask.id,
                text = subtask.text,
                textFontSize = 14,
                subtext = null,
                isCompleted = subtask.isCompleted,
                checkboxSize = 20,
                date = null,
                options = null,
                onEvent = onEvent,
                isBookmarked = null,
                modifier = Modifier.padding(start = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        NewSubtaskListItem(
            parentId = task.id,
            onEvent = onEvent,
            modifier = Modifier.padding(start = 22.dp)
        )
    }
}

@Composable
fun NewSubtaskListItem(
    parentId: Long,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable { onEvent(TaskConfiguratorPopupEvent.ShowPopup(parentId = parentId)) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_circle),
            contentDescription = null,
            tint = DarkBlue
        )

        Text(
            text = stringResource(id = R.string.add_new_subtask),
            color = Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskBlockListItemPreview() {
    TaskBlockListItem(
        onEvent = {},
        task = TaskWithTask(
            1,
            "Название задачи 1",
            isCompleted = false,
            isBookmarked = false,
            description = "weww",
            subtasks = listOf(
                Task(2, "Подзадача 1", isCompleted = false),
                Task(2, "Подзадача 2", isCompleted = false),
                Task(2, "Подзадача 3", isCompleted = false),
                Task(2, "Подзадача 3", isCompleted = false)
            )
        )
    )
}


@Preview(showBackground = true)
@Composable
fun TaskListItemPreview() {
    TaskListItem(
        text = "Название задачи №1",
        subtext = "Краткая заметка",
        isCompleted = false,
        textFontSize = 16,
        checkboxSize = 30,
        date = "2024/01/02",
        options = true,
        id = 1,
        onEvent = {},
        isBookmarked = false,
        modifier = Modifier
            .height(100.dp)
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun NewSubtaskListItemPreview() {
    NewSubtaskListItem(
        parentId = 1,
        onEvent = {},
        modifier = Modifier.fillMaxWidth()
    )
}

