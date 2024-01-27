@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.appcash.view.tasks.task.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.tasks.task.components.TaskEvent
import com.example.appcash.view.tasks.task.components.TaskType
import com.example.appcash.view.tasks.task.components.TasksState

@Composable
fun TaskList(
    state: TasksState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTaskId = remember { mutableLongStateOf(0) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(bottom = 50.dp),
        modifier = modifier
    ) {
        item { Header(state.folderName) }

        item {
            SearchTextField(
                searchQuery = state.querySearch,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
        }

        itemsIndexed(
            items = state.values.toList()
        ) { index, item ->
            TaskBlock(
                task = item.first,
                subtask = item.second,
                onEvent = onEvent,
                taskId = selectedTaskId
            )

            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 15.dp)
            ).takeIf { index < state.values.size - 1 }

        }
        item {
            AddTaskRow(
                text = "Добавить задачу",
                painter = painterResource(id = R.drawable.add_task_icon),
                textStyle = TextStyle(color = Color.Gray, fontSize = 22.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onEvent(TaskEvent.ShowMaintaskBottomSheetEvent) }
            )
        }
    }

    if (state.isShowed.first) {
        MaintaskModalBottomSheet(
            onEvent = onEvent
        )
    }

    if (state.isShowed.second) {
        SubtaskModalBottomSheet(
            mainTaskId = selectedTaskId.longValue,
            onEvent = onEvent
        )
    }
}

@Composable
fun TaskBlock(
    task: MainTask,
    taskId: MutableLongState,
    onEvent: (Event) -> Unit,
    subtask: List<SubTask>?
) {
    Column {
        TaskRow(
            id = task.id,
            text = task.text,
            isChecked = task.isCompleted,
            type = TaskType.MAIN,
            onEvent = onEvent,
            textStyle = TextStyle(
                color = if (task.isCompleted) Color.Gray else Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier.fillMaxWidth()
        )
        subtask?.forEach { subtask ->
            TaskRow(
                id = subtask.id,
                text = subtask.text,
                isChecked = subtask.isCompleted,
                type = TaskType.SUB,
                textStyle = TextStyle(
                    color = if (subtask.isCompleted) Color.Gray else Color.Black,
                    fontSize = 16.sp,
                    textDecoration = if (subtask.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                ),
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
            )
        }
        AddTaskRow(
            text = "Добавить подзадачу",
            painter = painterResource(id = R.drawable.add_task_icon),
            textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 43.dp)
                .clickable {
                    onEvent(TaskEvent.ShowSubtaskBottomSheetEvent)
                    taskId.longValue = task.id
                }
        )
    }
}


@Composable
fun TaskRow(
    id: Long,
    text: String,
    isChecked: Boolean,
    type: TaskType,
    textStyle: TextStyle,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
    checkBoxSize: Float = 1f
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onEvent(
                    TaskEvent.UpdateCheckBoxEvent(
                        id = id,
                        isChecked = it,
                        type = type
                    )
                )
            },
            modifier = Modifier.scale(checkBoxSize)
        )
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Composable
fun AddTaskRow(
    text: String,
    textStyle: TextStyle,
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Composable
fun MaintaskModalBottomSheet(
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val rememberTitle = remember { mutableStateOf("") }
    val rememberText = remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onEvent(BottomSheetEvent.HideEvent) },
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = rememberTitle.value,
                onValueChange = { rememberTitle.value = it },
                placeholder = { Text(text = "Название") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Button(
                    onClick = { onEvent(BottomSheetEvent.HideEvent) },
                    modifier = Modifier.weight(0.5f)
                ) {
                    androidx.compose.material.Text(
                        text = "Отменить",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = {
                        onEvent(
                            TaskEvent.CreateMaintaskEvent(
                                text = rememberTitle.value
                            )
                        )
                        onEvent(BottomSheetEvent.HideEvent)
                    },
                    modifier = Modifier.weight(0.5f)
                ) {
                    androidx.compose.material.Text(
                        text = "Сохранить",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }

    }
}

@Composable
private fun SubtaskModalBottomSheet(
    mainTaskId: Long,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val rememberText = remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onEvent(BottomSheetEvent.HideEvent) },
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = rememberText.value,
                onValueChange = { rememberText.value = it },
                placeholder = { Text(text = "Название подзадачи") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
            ) {
                Button(
                    onClick = { onEvent(BottomSheetEvent.HideEvent) },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        text = "Отменить",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = {
                        onEvent(
                            TaskEvent.CreateSubtaskEvent(
                                mainTaskId = mainTaskId,
                                text = rememberText.value
                            )
                        )
                        onEvent(BottomSheetEvent.HideEvent)
                    },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        text = "Сохранить",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}