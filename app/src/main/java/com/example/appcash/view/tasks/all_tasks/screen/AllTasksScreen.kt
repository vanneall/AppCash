package com.example.appcash.view.tasks.all_tasks.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.general.ErrorScreen
import com.example.appcash.view.tasks.all_tasks.components.AllTasksFoldersViewModel

@Composable
fun AllTasksScreen(
    viewModel: AllTasksFoldersViewModel,
    navigateTo: (String) -> Unit,
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Папки с задачами"
    )
    when (viewModel.state.collectAsState().value.isError) {
        false -> AllTasks(
            state = viewModel.state.collectAsState().value,
            onEvent = viewModel::handle,
            navigate = navigateTo,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        true -> ErrorScreen()
    }

}