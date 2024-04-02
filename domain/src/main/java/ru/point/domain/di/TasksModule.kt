package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.implementations.DeleteTaskByIdUseCaseImpl
import ru.point.domain.tasks.implementations.GetAllTasksCountUseCaseImpl
import ru.point.domain.tasks.implementations.GetPlannedCountUseCaseImpl
import ru.point.domain.tasks.implementations.GetTaskUseCase
import ru.point.domain.tasks.implementations.InsertTaskUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskUseCaseImpl
import ru.point.domain.tasks.interfaces.DeleteTaskByIdUseCase
import ru.point.domain.tasks.interfaces.GetAllTasksCountUseCase
import ru.point.domain.tasks.interfaces.GetPlannedCountUseCase
import ru.point.domain.tasks.interfaces.GetTasksUseCase
import ru.point.domain.tasks.interfaces.InsertTaskUseCase
import ru.point.domain.tasks.interfaces.UpdateTaskUseCase

@Module
@InstallIn(ViewModelComponent::class)
class TasksModule {
    @Provides
    @ViewModelScoped
    fun provideGetCompletedCountUseCase(repository: TasksRepository): GetAllTasksCountUseCase {
        return GetAllTasksCountUseCaseImpl(repository = repository)
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

    @Provides
    @ViewModelScoped
    fun provideDeleteTaskByIdUseCase(repository: TasksRepository): DeleteTaskByIdUseCase {
        return DeleteTaskByIdUseCaseImpl(repository = repository)
    }
}