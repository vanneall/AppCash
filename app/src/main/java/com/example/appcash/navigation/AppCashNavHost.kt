package com.example.appcash.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.appcash.navigation.Destinations.FOLDERS_SCREEN
import com.example.appcash.navigation.Destinations.FOLDER_TO_NOTE_LINK_SCREEN
import com.example.appcash.navigation.Destinations.NOTE_INFO_SCREEN
import com.example.appcash.view.notes.note_info.NoteInfoScreen
import com.example.appcash.view.notes.note_info.components.NoteInfoViewModelFactoryProvider
import com.example.appcash.view.notes.notes_folders.NotesFoldersScreen
import com.example.appcash.view.notes.notes_folders.components.FoldersListViewModel
import com.example.appcash.view.notes.notes_list.NotesListScreen
import com.example.appcash.view.notes.notes_list.components.NoteListViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors


const val NEW_NOTE_INDEX = -1

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
            route = "$FOLDER_TO_NOTE_LINK_SCREEN/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                }
            )
        ) {
            val factory = EntryPointAccessors.fromActivity(
                LocalContext.current as Activity,
                NoteListViewModelFactoryProvider::class.java
            ).provideNoteListViewModelFactory()

            val viewModel = viewModel { factory.create(it.arguments?.getLong("id") ?: 0) }

            NotesListScreen(
                viewModel = viewModel,
                navigateBack = navController::popBackStack,
                navigateTo = navController::navigate
            )
        }

        composable(
            route = "$NOTE_INFO_SCREEN/{id}/{folderId}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                },
                navArgument(name = "folderId") {
                    type = NavType.LongType
                }
            )
        ) {
            val factory = EntryPointAccessors.fromActivity(
                LocalContext.current as Activity,
                NoteInfoViewModelFactoryProvider::class.java
            ).provideNoteInfoViewModelFactory()
            val viewModel = viewModel {
                factory.create(
                    it.arguments?.getLong("id") ?: 0,
                    it.arguments?.getLong("folderId") ?: 0
                )
            }
            NoteInfoScreen(
                viewModel = viewModel,
                navigateBack = navController::popBackStack
            )
        }
    }
}
