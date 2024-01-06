package com.example.appcash.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appcash.view.notes.note_info.NoteInfoScreen
import com.example.appcash.view.notes.notes_folders.NotesFoldersScreen
import com.example.appcash.view.notes.notes_list.NotesListScreen
import com.example.appcash.view.notes.notes_list.components.FactoryProvider
import com.example.appcash.view.notes.notes_list.components.MyFactory
import com.example.appcash.view.notes.notes_list.components.NotesListViewModel
import com.example.appcash.view.ui.theme.AppCashTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCashTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesListScreen()
                }
            }
        }
    }
}