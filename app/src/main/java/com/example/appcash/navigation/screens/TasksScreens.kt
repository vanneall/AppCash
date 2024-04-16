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
import com.example.appcash.view.tasks.list.components.TasksListViewModel
import com.example.appcash.view.tasks.list.components.TasksSelection
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
        route = Destinations.MAIN_TASKS_SCREEN
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

fun TasksListScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = "${Destinations.TASKS_LIST_SCREEN}/{${ArgsKeys.OPEN_MODE_KEY}}/{${ArgsKeys.CATEGORY_ID_KEY}}",

        arguments = listOf(
            navArgument(name = ArgsKeys.OPEN_MODE_KEY) {
                type = NavType.StringType
            },
            navArgument(name = ArgsKeys.CATEGORY_ID_KEY) {
                type = NavType.LongType
            }
        )
    ) { backStackEntry ->
        val assistedFactory = EntryPointAccessors.fromActivity(
            activity = LocalContext.current as Activity,
            entryPoint = TasksViewModelFactoryProvider::class.java
        ).provideTasksViewModelFactory()

        val folderIdArg: Long?
        val openModeHandledArg: TasksSelection
        with(backStackEntry) {
            folderIdArg = arguments?.getLong(ArgsKeys.CATEGORY_ID_KEY).takeIf { id -> id != 0L }
            val openModeArg = arguments?.getString(ArgsKeys.OPEN_MODE_KEY)
            openModeHandledArg = TasksSelection.handle(mode = openModeArg)
        }

        TasksSelection.checkIfScreenRulePassed(id = folderIdArg, selections = openModeHandledArg)


        val viewModel: TasksListViewModel = viewModel {
            assistedFactory.create(
                categoryId = folderIdArg,
                openMode = openModeHandledArg,
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