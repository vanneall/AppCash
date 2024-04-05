@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appcash.navigation.Destinations
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.notes.notefolders.components.FolderOpenMode
import com.example.appcash.view.tasks.list.components.TasksSelections
import com.example.appcash.view.tasks.list.components.TasksViewModelFactoryProvider
import com.example.appcash.view.tasks.list.screen.TasksListScreen
import com.example.appcash.view.tasks.main.components.TasksMainViewModel
import com.example.appcash.view.tasks.main.screen.TasksMainScreen
import dagger.hilt.android.EntryPointAccessors

fun MainTasksScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_TASKS_FOLDER_SCREEN
    ) {
        val viewModel: TasksMainViewModel = hiltViewModel()
        TasksMainScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}

fun TasksScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = "${Destinations.TASKS_SCREEN}/{${ArgsKeys.OPEN_MODE_KEY}}/{${ArgsKeys.FOLDER_ID_KEY}}",

        arguments = listOf(
            navArgument(name = ArgsKeys.OPEN_MODE_KEY) {
                type = NavType.StringType
            },
            navArgument(name = ArgsKeys.FOLDER_ID_KEY) {
                type = NavType.LongType
            }
        )
    ) { backStackEntry ->
        val factory = EntryPointAccessors.fromActivity(
            activity = LocalContext.current as Activity,
            entryPoint = TasksViewModelFactoryProvider::class.java
        ).provideTasksViewModelFactory()

        val openModeString = backStackEntry
            .arguments
            ?.getString(ArgsKeys.OPEN_MODE_KEY)
            ?: FolderOpenMode.Definition.ERROR_VALUE_STRING

        val openModeEnum = TasksSelections.handle(mode = openModeString)

        val folderId = backStackEntry.arguments?.getLong(ArgsKeys.FOLDER_ID_KEY)

        val viewModel = viewModel {
            factory.create(
                openMode = openModeEnum,
                folderId = folderId.takeIf { folderId != null && folderId > (0).toLong() }

            )
        }

        TasksListScreen(
            viewModel = viewModel,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}