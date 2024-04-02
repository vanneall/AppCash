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
import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.ArgsKeys.ID_KEY
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.notes.info.components.NoteInfoViewModelFactoryProvider
import com.example.appcash.view.notes.info.screen.NoteInfoScreen
import com.example.appcash.view.notes.notefolders.components.MainNotesViewModel
import com.example.appcash.view.notes.notefolders.screen.MainNotesScreen
import com.example.appcash.view.notes.notes.components.NoteListViewModelFactoryProvider
import com.example.appcash.view.notes.notes.screen.NotesListScreen
import dagger.hilt.android.EntryPointAccessors

fun MainNotesScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_NOTES_FOLDER_SCREEN
    ) {
        val viewModel: MainNotesViewModel = hiltViewModel()
        MainNotesScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}

fun NotesListScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = "${Destinations.NOTES_LIST_SCREEN}/{$ID_KEY}",

        arguments = listOf(
            navArgument(name = ID_KEY) {
                type = NavType.LongType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->

        val factory = EntryPointAccessors.fromActivity(
            activity = LocalContext.current as Activity,
            entryPoint = NoteListViewModelFactoryProvider::class.java
        ).provideNoteListViewModelFactory()

        val folderId = backStackEntry.arguments?.getLong(ID_KEY).takeIf { id -> id != (0).toLong() }

        val viewModel = viewModel {
            factory.create(
                folderId = folderId
            )
        }

        NotesListScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}

fun NoteScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>
) {
    navGraphBuilder.composable(
        route = "${Destinations.NOTE_SCREEN}/{$FOLDER_ID_KEY}/{$ID_KEY}",

        arguments = listOf(
            navArgument(name = ID_KEY) {
                type = NavType.LongType
                defaultValue = 0
            },
            navArgument(name = FOLDER_ID_KEY) {
                type = NavType.LongType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val factory = EntryPointAccessors.fromActivity(
            activity = LocalContext.current as Activity,
            entryPoint = NoteInfoViewModelFactoryProvider::class.java
        ).provideNoteInfoViewModelFactory()
        val viewModel = viewModel {
            factory.create(
                noteId = backStackEntry.arguments?.getLong(ID_KEY).takeIf { it != (0).toLong() },
                folderId = backStackEntry.arguments?.getLong(FOLDER_ID_KEY)
                    .takeIf { it != (0).toLong() }
            )
        }

        NoteInfoScreen(
            viewModel = viewModel,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState
        )
    }
}