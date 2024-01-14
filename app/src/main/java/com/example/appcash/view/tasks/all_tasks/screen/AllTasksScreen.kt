package com.example.appcash.view.tasks.all_tasks.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcash.view.tasks.all_tasks.components.AllTasksFoldersViewModel

@Composable
fun AllTasksScreen(
    viewModel: AllTasksFoldersViewModel,
    navigateTo: (String) -> Unit
) {
    AllTasks(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}