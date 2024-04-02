package com.example.appcash.view.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun EditPopup(
    state: EditPopupState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Option(
            name = stringResource(id = R.string.folder),
            value = "",
            hint = "Пока недоступно",
            onEvent = { }
        )

        Option(
            name = stringResource(id = R.string.task_name),
            hint = stringResource(id = R.string.task_name_hint),
            value = state.name,
            onEvent = { value -> onEvent(EditPopupEvent.InsertName(value)) }
        )

        Option(
            name = stringResource(id = R.string.task_description),
            hint = stringResource(id = R.string.task_description_hint),
            value = state.description,
            onEvent = { value -> onEvent(EditPopupEvent.InsertDescription(value)) }
        )

        Option(
            name = stringResource(id = R.string.date_and_time_of_end),
            hint = "Пока недоступно",
            value = "",
            onEvent = { }
        )

        Button(
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
            onClick = { onEvent(EditPopupEvent.CreateTask(state.parentId)) },
            modifier = Modifier.size(52.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
private fun Option(
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
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightGray,
                focusedContainerColor = LightGray
            )
        )
    }
}