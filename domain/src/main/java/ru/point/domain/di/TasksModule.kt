package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.implementations.GetCompletedCountUseCaseImpl
import ru.point.domain.tasks.implementations.GetPlannedCountUseCaseImpl
import ru.point.domain.tasks.implementations.GetTaskUseCase
import ru.point.domain.tasks.implementations.InsertTaskUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskUseCaseImpl
import ru.point.domain.tasks.interfaces.GetCompletedCountUseCase
import ru.point.domain.tasks.interfaces.GetPlannedCountUseCase
import ru.point.domain.tasks.interfaces.GetTasksUseCase
import ru.point.domain.tasks.interfaces.InsertTaskUseCase
import ru.point.domain.tasks.interfaces.UpdateTaskUseCase

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
    fun provideGetMapTasksUseCase(repository: TasksRepository): GetTasksUseCase {
        return GetTaskUseCase(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMainTasksUseCase(repository: TasksRepository): InsertTaskUseCase {
        return InsertTaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateTasksUseCase(repository: TasksRepository): UpdateTaskUseCase {
        return UpdateTaskUseCaseImpl(repository = repository)
    }
}