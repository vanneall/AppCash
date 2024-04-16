package com.example.appcash.view.tasks.list.components

import com.example.appcash.utils.ArgsKeys.CATEGORY_ID_KEY
import com.example.appcash.utils.ArgsKeys.OPEN_MODE_KEY
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface TaskListAssistedFactory {
    fun create(
        @Assisted(OPEN_MODE_KEY) openMode: TasksSelection,
        @Assisted(CATEGORY_ID_KEY) categoryId: Long?
    ): TasksListViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface TasksViewModelFactoryProvider {
    fun provideTasksViewModelFactory(): TaskListAssistedFactory
}