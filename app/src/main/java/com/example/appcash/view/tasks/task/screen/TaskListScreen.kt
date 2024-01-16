package com.example.appcash.view.tasks.task.screen

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.view.notes.notes_folders_screen.components.FolderOpenMode
import com.example.appcash.view.notes.notes_list.components.NoteListViewModelFactoryProvider
import com.example.appcash.view.tasks.task.components.TasksViewModel
import com.example.appcash.view.tasks.task.components.TasksViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors

@Composable
fun TaskListScreen() {

    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        TasksViewModelFactoryProvider::class.java
    ).provideTasksViewModelFactory()

    val viewModel = viewModel { factory.create(FolderOpenMode.ALL, 0) }

    TaskList(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}