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
import com.example.appcash.data.entities.TaskWithTask
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.tasks.task.components.TaskEvent
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
            items = state.values
        ) { index, item ->
            TaskBlock(
                task = item,
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
                    .clickable {
                        onEvent(TaskEvent.ShowTaskBottomSheetEvent)
                        selectedTaskId.longValue = 0
                    }
            )
        }
    }

    if (state.isShowed.first) {
        TaskModalBottomSheet(
            onEvent = onEvent,
            parentTaskId = selectedTaskId.longValue,
        )
    }
}

@Composable
fun TaskBlock(
    task: TaskWithTask,
    taskId: MutableLongState,
    onEvent: (Event) -> Unit,
) {
    Column {
        TaskRow(
            id = task.id,
            text = task.text,
            isChecked = task.isCompleted,
            onEvent = onEvent,
            textStyle = TextStyle(
                color = if (task.isCompleted) Color.Gray else Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier.fillMaxWidth()
        )
        task.subtasks.forEach { subtask ->
            TaskRow(
                id = subtask.id,
                text = subtask.text,
                isChecked = subtask.isCompleted,
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
                    onEvent(TaskEvent.ShowTaskBottomSheetEvent)
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
            onCheckedChange = { isChecked ->
                onEvent(
                    TaskEvent.UpdateCompletedState(
                        id = id,
                        isChecked = isChecked,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskModalBottomSheet(
    parentTaskId: Long,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    val rememberTitle = remember { mutableStateOf("") }

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
                            TaskEvent.CreateTask(
                                text = rememberTitle.value,
                                parentTaskId = parentTaskId
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