package com.example.appcash.view.notes.note_info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.view.Event
import com.example.appcash.view.notes.TopBar
import com.example.appcash.view.notes.note_info.components.NoteInfoEvent
import com.example.appcash.view.notes.note_info.components.NoteInfoState

@Composable
fun NoteInfoField(
    state: NoteInfoState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollableState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollableState),
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(27.dp))
        Box {
            BasicTextField(
                value = state.title,
                onValueChange = {
                    onEvent(
                        NoteInfoEvent.InputTitleEvent(it)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 22.sp,
                ),

            )
            if (state.title.isEmpty()) {
                Text(
                    text = "Название",
                    fontSize = 22.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box {
            BasicTextField(
                value = state.content,
                onValueChange = {
                    onEvent(
                        NoteInfoEvent.InputContentEvent(it)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 17.sp
                )
            )
            if (state.content.isEmpty()) Text(
                text = "Текст",
                fontSize = 17.sp,
                color = Color.Gray
            )
        }

    }
}

@Preview
@Composable
fun PreviewNoteInfoField() {
    //NoteInfoField(modifier = Modifier.padding(horizontal = 20.dp))
}