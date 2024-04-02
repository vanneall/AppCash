package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.domain.notes.implementations.DeleteFolderByIdImpl
import ru.point.domain.notes.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetFolderByTypeUseCaseImpl
import ru.point.domain.notes.implementations.InsertFolderUseCaseImpl
import ru.point.domain.notes.implementations.UpdateCategoryUseCaseImpl
import ru.point.domain.notes.interfaces.DeleteByIdFolderUseCase
import ru.point.domain.notes.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.notes.interfaces.GetCategoryNameByIdUseCase
import ru.point.domain.notes.interfaces.InsertFolderUseCase
import ru.point.domain.notes.interfaces.UpdateFolderUseCase

@Module
@InstallIn(ViewModelComponent::class)
class FoldersModule {
    @Provides
    @ViewModelScoped
    fun provideDeleteFolderByIdUseCase(repository: CategoriesRepository): DeleteByIdFolderUseCase {
        return DeleteFolderByIdImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateFolderUseCase(repository: CategoriesRepository): UpdateFolderUseCase {
        return UpdateCategoryUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFoldersUseCase(repository: CategoriesRepository): GetCategoryByTypeUseCase {
        return GetFolderByTypeUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFolderUseCase(repository: CategoriesRepository): InsertFolderUseCase {
        return InsertFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFolderNameByIdUseCase(repository: CategoriesRepository): GetCategoryNameByIdUseCase {
        return GetCategoryNameByIdUseCaseImpl(repository = repository)
    }
}