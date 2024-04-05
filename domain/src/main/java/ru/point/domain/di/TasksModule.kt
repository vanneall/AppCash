package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.implementations.DeleteTaskByIdUseCaseImpl
import ru.point.domain.tasks.implementations.GetAllTasksCountUseCaseImpl
import ru.point.domain.tasks.implementations.GetBookmarksCountUseCaseImpl
import ru.point.domain.tasks.implementations.GetBookmarksTasksUseCaseImpl
import ru.point.domain.tasks.implementations.GetTaskUseCaseImpl
import ru.point.domain.tasks.implementations.InsertTaskUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskBookmarkedUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskCheckedUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskUseCaseImpl
import ru.point.domain.tasks.interfaces.DeleteTaskByIdUseCase
import ru.point.domain.tasks.interfaces.GetAllTasksCountUseCase
import ru.point.domain.tasks.interfaces.GetBookmarksCountUseCase
import ru.point.domain.tasks.interfaces.GetBookmarksTasksUseCase
import ru.point.domain.tasks.interfaces.GetTasksUseCase
import ru.point.domain.tasks.interfaces.InsertTaskUseCase
import ru.point.domain.tasks.interfaces.UpdateTaskCheckedUseCase
import ru.point.domain.tasks.interfaces.UpdateTaskUseCase
import ru.point.domain.tasks.interfaces.UpdateTasksBookmarkedUseCase

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
    fun provideGetPlannedCountUseCase(repository: TasksRepository): GetBookmarksCountUseCase {
        return GetBookmarksCountUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMapTasksUseCase(repository: TasksRepository): GetTasksUseCase {
        return GetTaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMainTasksUseCase(repository: TasksRepository): InsertTaskUseCase {
        return InsertTaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateTasksUseCase(repository: TasksRepository): UpdateTaskCheckedUseCase {
        return UpdateTaskCheckedUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteTaskByIdUseCase(repository: TasksRepository): DeleteTaskByIdUseCase {
        return DeleteTaskByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetBookmarkTasksUseCase(repository: TasksRepository): GetBookmarksTasksUseCase {
        return GetBookmarksTasksUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateBookmarkedUseCase(repository: TasksRepository): UpdateTasksBookmarkedUseCase {
        return UpdateTaskBookmarkedUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateTaskUseCase(repository: TasksRepository): UpdateTaskUseCase {
        return UpdateTaskUseCaseImpl(repository = repository)
    }
}