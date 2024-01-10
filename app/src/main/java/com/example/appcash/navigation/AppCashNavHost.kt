package com.example.appcash.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appcash.navigation.Destinations.FOLDERS_SCREEN
import com.example.appcash.navigation.Destinations.FOLDER_TO_NOTE_LINK_SCREEN
import com.example.appcash.navigation.Destinations.NOTE_INFO_SCREEN
import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.ArgsKeys.ID_KEY
import com.example.appcash.utils.ArgsKeys.OPEN_MODE_KEY
import com.example.appcash.view.notes.note_info_screen.components.NoteInfoViewModelFactoryProvider
import com.example.appcash.view.notes.note_info_screen.components.NoteOpenMode
import com.example.appcash.view.notes.note_info_screen.screen.NoteInfoScreen
import com.example.appcash.view.notes.notes_folders_screen.components.FolderOpenMode
import com.example.appcash.view.notes.notes_folders_screen.components.FoldersListViewModel
import com.example.appcash.view.notes.notes_folders_screen.screen.NotesFoldersScreen
import com.example.appcash.view.notes.notes_list.NotesListScreen
import com.example.appcash.view.notes.notes_list.components.NoteListViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors


@Composable
fun AppCashNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = FOLDERS_SCREEN
    ) {
        composable(route = FOLDERS_SCREEN) {
            val foldersListViewModel: FoldersListViewModel = hiltViewModel()
            NotesFoldersScreen(
                viewModel = foldersListViewModel,
                navigateTo = navController::navigate
            )
        }

        composable(
            route = "$FOLDER_TO_NOTE_LINK_SCREEN/{$OPEN_MODE_KEY}/{$ID_KEY}",
            arguments = listOf(
                navArgument(name = OPEN_MODE_KEY) {
                    type = NavType.StringType
                },
                navArgument(name = ID_KEY) {
                    type = NavType.LongType
                }
            )
        ) {backStackEntry ->
            val factory = EntryPointAccessors.fromActivity(
                LocalContext.current as Activity,
                NoteListViewModelFactoryProvider::class.java
            ).provideNoteListViewModelFactory()

            val modeString = backStackEntry.arguments?.getString(OPEN_MODE_KEY)
                ?: FolderOpenMode.Definition.DEFAULT_VALUE_STRING

            val modeEnum = FolderOpenMode.Definition.handle(mode = modeString)

            val viewModel = viewModel { factory.create(modeEnum,backStackEntry.arguments?.getLong(ID_KEY) ?: 0) }

            NotesListScreen(
                viewModel = viewModel,
                navigateBack = navController::popBackStack,
                navigateTo = navController::navigate
            )
        }

        composable(
            route = "$NOTE_INFO_SCREEN/{$OPEN_MODE_KEY}/{$ID_KEY}/{$FOLDER_ID_KEY}",
            arguments = listOf(
                navArgument(name = OPEN_MODE_KEY) {
                  type = NavType.StringType
                },
                navArgument(name = ID_KEY) {
                    type = NavType.LongType
                },
                navArgument(name = FOLDER_ID_KEY) {
                    type = NavType.LongType
                }
            )
        ) {backStackEntry ->
            val factory = EntryPointAccessors.fromActivity(
                LocalContext.current as Activity,
                NoteInfoViewModelFactoryProvider::class.java
            ).provideNoteInfoViewModelFactory()

            val modeString = backStackEntry.arguments?.getString(OPEN_MODE_KEY)
                ?: NoteOpenMode.Definition.DEFAULT_VALUE_STRING

            val modeEnum = NoteOpenMode.Definition.handle(mode = modeString)

            val viewModel = viewModel {
                factory.create(
                    mode = modeEnum,
                    id = backStackEntry.arguments?.getLong(ID_KEY) ?: 0,
                    folderId = backStackEntry.arguments?.getLong(FOLDER_ID_KEY) ?: 0
                )
            }

            NoteInfoScreen(
                viewModel = viewModel,
                navigateBack = navController::popBackStack
            )
        }
    }
}
