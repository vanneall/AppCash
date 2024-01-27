package com.example.appcash.view.general.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.appcash.R
import com.example.appcash.view.notes.notes_folder.components.MainNotesEvent

@Composable
fun CreateFolderDialogView(
    onCreateEvent: (MainNotesEvent) -> Unit,
    isDialogOpenedMutableState: MutableState<Boolean>,
) {
    val name = remember { mutableStateOf("") }
    Dialog(onDismissRequest = { isDialogOpenedMutableState.value = false }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(corner = CornerSize(20.dp))
                )
                .width(300.dp)
                .height(180.dp)
                .padding(15.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = name.value,
                onValueChange = { text -> name.value = text },
                label = { Text(text = stringResource(id = R.string.folder_name)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CreateCancelButtonsRowView(
                onEvent = onCreateEvent,
                isDialogOpenedMutableState = isDialogOpenedMutableState,
                name = name.value
            )
        }
    }
}


@Composable
private fun CreateCancelButtonsRowView(
    onEvent: (MainNotesEvent) -> Unit,
    isDialogOpenedMutableState: MutableState<Boolean>,
    name: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { isDialogOpenedMutableState.value = false }
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
        Button(
            onClick = {
                onEvent(
                    MainNotesEvent.InsertFolderEvent(
                        name = name,
                        colorIndex = 0
                    )
                )
                isDialogOpenedMutableState.value = false
            }) {
            Text(text = stringResource(id = R.string.create))
        }
    }
}