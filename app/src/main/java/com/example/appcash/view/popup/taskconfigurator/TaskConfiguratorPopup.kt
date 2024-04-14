package com.example.appcash.view.popup.taskconfigurator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.mode.toLocalDate
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskConfiguratorPopup(
    state: TaskConfiguratorPopupState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val datePickerState = rememberDatePickerState()

        TextFieldOption(
            name = stringResource(id = R.string.folder),
            value = "",
            hint = "Пока недоступно",
            onEvent = { }
        )

        TextFieldOption(
            name = stringResource(id = R.string.task_name),
            hint = stringResource(id = R.string.task_name_hint),
            value = state.name,
            onEvent = { value -> onEvent(TaskConfiguratorPopupEvent.InsertName(value)) }
        )

        TextFieldOption(
            name = stringResource(id = R.string.task_description),
            hint = stringResource(id = R.string.task_description_hint),
            value = state.description,
            onEvent = { value -> onEvent(TaskConfiguratorPopupEvent.InsertDescription(value)) }
        )

        DialogOption(
            name = stringResource(id = R.string.date_of_end),
            value = state.date.toString(),
            onClick = { onEvent(TaskConfiguratorPopupEvent.ShowDatePickerDialog) },
        )

        IconButton(
            onClick = { onEvent(TaskConfiguratorPopupEvent.CreateTask(state.parentId)) },
            modifier = Modifier.background(color = DarkBlue, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White
            )
        }

        if (state.isDatePickerShowed) {
            DatePickerDialog(
                onDismissRequest = { onEvent(TaskConfiguratorPopupEvent.HideDatePickerDialog) },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { timeInMills ->
                                onEvent(TaskConfiguratorPopupEvent.SelectDate(timeInMills.toLocalDate()))
                            }
                            onEvent(TaskConfiguratorPopupEvent.HideDatePickerDialog)
                        }
                    ) {
                        Text(text = "Выбрать")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Composable
private fun TextFieldOption(
    name: String,
    value: String,
    hint: String,
    onEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = value,
            onValueChange = { onEvent(it) },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(fontSize = 14.sp),
            label = { Text(text = hint) },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightGray,
                focusedContainerColor = LightGray
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DialogOption(
    name: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(LightGray, shape = RoundedCornerShape(15.dp))
                .clickable { onClick() }
        ) {
            Text(
                text = value,
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
        }

    }
}