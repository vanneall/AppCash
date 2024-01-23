package com.example.appcash.view.notes.note_info_screen.screen

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.view.notes.note_info_screen.components.NoteInfoEvent
import com.example.appcash.view.notes.note_info_screen.components.NoteInfoState

@Composable
fun NoteInfo(
    state: NoteInfoState,
    onEvent: (Event) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollableState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollableState),
    ) {
        Spacer(modifier = Modifier.height(27.dp))
        TitleTextField(state = state, onEvent = onEvent)
        Spacer(modifier = Modifier.height(20.dp))
        ContentTextField(state = state, onEvent = onEvent)
    }
    BackHandler {
        onEvent(
            NoteInfoEvent.SaveNoteEvent(
                title = state.title,
                content = state.content
            )
        )
        onNavigateBack()
    }
}

@Composable
private fun TitleTextField(
    state: NoteInfoState,
    onEvent: (Event) -> Unit,
) {
    Box {
        BasicTextField(
            value = state.title,
            onValueChange = {
                onEvent(
                    NoteInfoEvent.InputTitleEvent(it)
                )
            },
            textStyle = TextStyle(fontSize = 22.sp),
        )
        if (state.title.isEmpty()) {
            Text(
                text = stringResource(id = R.string.title),
                fontSize = 22.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun ContentTextField(
    state: NoteInfoState,
    onEvent: (Event) -> Unit,
) {
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
            text = stringResource(id = R.string.content),
            fontSize = 17.sp,
            color = Color.Gray
        )
    }
}