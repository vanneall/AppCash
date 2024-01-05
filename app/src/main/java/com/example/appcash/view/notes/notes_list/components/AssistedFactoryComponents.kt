package com.example.appcash.view.notes.notes_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface MyFactory {
    fun create(folderId: Long): NotesListViewModel
}

object FactoryProvider {
    fun provideViewModel(factory: MyFactory, folderId: Long): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(folderId) as T
            }
        }
    }
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface NoteListViewModelFactoryProvider {
    fun provideNoteListViewModelFactory(): MyFactory
}