package com.example.appcash.view.notes.info.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.events.Event
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.general.ErrorScreen
import com.example.appcash.view.notes.info.components.NoteInfoEvent
import com.example.appcash.view.notes.info.components.NoteInfoState
import com.example.appcash.view.notes.info.components.NoteInfoViewModel
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun NoteInfoScreen(
    viewModel: NoteInfoViewModel,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "",
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

    when (viewModel.state.collectAsState().value.isError) {
        false -> NoteInfo(
            state = viewModel.state.collectAsState().value,
            onEvent = viewModel::handle,
            onNavigateBack = navigateBack,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        true -> ErrorScreen()
    }

}

@Composable
private fun NoteInfo(
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
        Spacer(modifier = Modifier.height(30.dp))

        TitleTextField(
            state = state,
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
        )

        ContentTextField(
            state = state,
            onEvent = onEvent,
            modifier = Modifier.fillMaxSize()
        )
    }
    BackHandler {
        onEvent(
            NoteInfoEvent.SaveNoteEvent
        )
        onNavigateBack()
    }
}

@Composable
private fun TitleTextField(
    state: NoteInfoState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
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
            modifier = modifier
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
    modifier: Modifier = Modifier
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
            ),
            modifier = modifier
        )
        if (state.content.isEmpty()) Text(
            text = stringResource(id = R.string.content),
            fontSize = 17.sp,
            color = Color.Gray
        )
    }
}