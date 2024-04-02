package com.example.appcash.view.tasks.task.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import ru.point.data.data.entities.Task
import ru.point.data.data.entities.TaskWithTask
import com.example.appcash.utils.events.Event
import com.example.appcash.view.tasks.task.components.TasksState
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.Gray

@Composable
fun TaskList(
    state: TasksState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = state.values,
            key = { task -> task.id }
        ) { task ->
            TaskBlockListItem(
                task = task,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TaskListItem(
    text: String,
    textFontSize: Int,
    checkboxSize: Int,
    subtext: String?,
    date: String?,
    options: Boolean?,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Checkbox(
            checked = isCompleted,
            onCheckedChange = {},
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

        Spacer(modifier = Modifier.width(16.dp))

        options?.let {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Иконка больше",
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun TaskBlockListItem(
    task: TaskWithTask,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TaskListItem(
            text = task.text,
            textFontSize = 16,
            //TODO добавить в заметки поле описания
            subtext = "Описание",
            checkboxSize = 24,
            isCompleted = task.isCompleted,
            date = "2024/01/02",
            options = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        task.subtasks.forEach { subtask ->
            TaskListItem(
                text = subtask.text,
                textFontSize = 14,
                subtext = null,
                isCompleted = true,
                checkboxSize = 20,
                date = null,
                options = null,
                modifier = Modifier.padding(start = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        NewSubtaskListItem(
            modifier = Modifier.padding(start = 22.dp)
        )
    }
}

@Composable
fun NewSubtaskListItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
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
        task = TaskWithTask(
            1, "Название задачи 1",
            isCompleted = false,
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
        modifier = Modifier
            .height(100.dp)
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun NewSubtaskListItemPreview() {
    NewSubtaskListItem(
        modifier = Modifier.fillMaxWidth()
    )
}

