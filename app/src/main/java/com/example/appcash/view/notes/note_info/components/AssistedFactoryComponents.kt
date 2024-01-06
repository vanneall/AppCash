package com.example.appcash.view.notes.note_info.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface Factory {
    fun create(id: Long): NoteInfoViewModel
}

object FactoryProvider {
    fun provideNoteInfoViewModelFactory(factory: Factory, id: Long): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(id = id) as T
            }
        }
    }
}


@EntryPoint
@InstallIn(ActivityComponent::class)
interface NoteInfoViewModelFactoryProvider {
    fun provideNoteInfoViewModelFactory(): Factory
}