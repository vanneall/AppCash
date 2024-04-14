package com.example.appcash.view.notes.notes.components

import com.example.appcash.utils.ArgsKeys.CATEGORY_ID_KEY
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface MyFactory {
    fun create(
        @Assisted(CATEGORY_ID_KEY) categoryId: Long?
    ): NotesListViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface NoteListViewModelFactoryProvider {
    fun provideNoteListViewModelFactory(): MyFactory
}