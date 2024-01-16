package com.example.appcash.view.tasks.task.screen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.entities.Task
import com.example.appcash.utils.events.Event
import com.example.appcash.view.general.list.Header
import com.example.appcash.view.general.other.SearchTextField
import com.example.appcash.view.general.other.TopBar
import com.example.appcash.view.tasks.task.components.TaskEvent
import com.example.appcash.view.tasks.task.components.TasksState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    state: TasksState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(bottom = 50.dp),
        modifier = modifier
    ) {
        stickyHeader { TopBar() }
        item { Header(state.folderName) }
        item { SearchTextField(state.querySearch, onEvent) }
        itemsIndexed(state.values.toList()) { index, item ->
            TaskBlock(item.first, onEvent, item.second)

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
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TaskBlock(
    task: MainTask,
    onEvent: (Event) -> Unit,
    subTask: List<SubTask>?
) {
    Column {
        TaskRow(
            task = task,
            onEvent = onEvent,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth()
        )
        subTask?.forEach {
            TaskRow(
                task = it,
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
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
            onEvent = onEvent,
            mainTaskId = task.id,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 43.dp)
        )
    }
}


@Composable
fun TaskRow(
    task: Task,
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
            checked = task.isCompleted,
            onCheckedChange = {
                onEvent(
                    TaskEvent.UpdateCheckBoxEvent(
                        task = task
                    )
                )
            },
            modifier = Modifier.scale(checkBoxSize)
        )
        Text(
            text = task.text,
            style = textStyle
        )
    }
}

@Composable
fun AddTaskRow(
    text: String,
    textStyle: TextStyle,
    onEvent: (Event) -> Unit,
    painter: Painter,
    modifier: Modifier = Modifier,
    mainTaskId: Long? = null,
) {
    Row(
        modifier = modifier.clickable {
            onEvent(
                TaskEvent.CreateTaskEvent(
                    mainTaskId = mainTaskId,
                    title = "Подзадача"
                )
            )
        },
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




//@Preview(showBackground = true)
//@Composable
//fun TaskRowPreview() {
//    TaskRow(
//        text = "Задача",
//        textStyle = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold),
//        modifier = Modifier.fillMaxWidth()
//    )
//}