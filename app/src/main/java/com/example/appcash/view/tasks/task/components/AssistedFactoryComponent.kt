package com.example.appcash.view.tasks.task.components

import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.ArgsKeys.OPEN_MODE_KEY
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface MyFactory {
    fun create(
        @Assisted(OPEN_MODE_KEY) openMode: FolderOpenMode,
        @Assisted(FOLDER_ID_KEY) folderId: Long
    ): TasksViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface TasksViewModelFactoryProvider {
    fun provideTasksViewModelFactory(): MyFactory
}