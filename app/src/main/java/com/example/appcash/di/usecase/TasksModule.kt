package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.implementations.GetCompletedCountUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetPlannedCountUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetTaskUseCase
import com.example.appcash.domain.tasks.implementations.InsertMaintaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.UpdateTaskUseCaseImpl
import com.example.appcash.domain.tasks.interfaces.GetCompletedCountUseCase
import com.example.appcash.domain.tasks.interfaces.GetMapTasksUseCase
import com.example.appcash.domain.tasks.interfaces.GetPlannedCountUseCase
import com.example.appcash.domain.tasks.interfaces.InsertMaintaskUseCase
import com.example.appcash.domain.tasks.interfaces.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class TasksModule {
    @Provides
    @ViewModelScoped
    fun provideGetCompletedCountUseCase(repository: TasksRepository): GetCompletedCountUseCase {
        return GetCompletedCountUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetPlannedCountUseCase(repository: TasksRepository): GetPlannedCountUseCase {
        return GetPlannedCountUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMapTasksUseCase(repository: TasksRepository): GetMapTasksUseCase {
        return GetTaskUseCase(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMainTasksUseCase(repository: TasksRepository): InsertMaintaskUseCase {
        return InsertMaintaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateTasksUseCase(repository: TasksRepository): UpdateTaskUseCase {
        return UpdateTaskUseCaseImpl(repository = repository)
    }
}