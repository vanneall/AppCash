package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.factory.CategoryFactory
import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.domain.category.implementations.CreateCategoryUseCaseImpl
import ru.point.domain.category.implementations.DeleteFolderByIdImpl
import ru.point.domain.category.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.category.implementations.GetFolderByTypeUseCaseImpl
import ru.point.domain.category.implementations.UpdateCategoryUseCaseImpl
import ru.point.domain.category.interfaces.CreateCategoryUseCase
import ru.point.domain.category.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.category.interfaces.GetCategoryNameByIdUseCase
import ru.point.domain.category.interfaces.UpdateFolderUseCase
import ru.point.domain.notes.interfaces.DeleteByIdFolderUseCase

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
    fun provideInsertFolderUseCase(
        repository: CategoriesRepository,
        factory: CategoryFactory
    ): CreateCategoryUseCase {
        return CreateCategoryUseCaseImpl(repository = repository, factory = factory)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFolderNameByIdUseCase(repository: CategoriesRepository): GetCategoryNameByIdUseCase {
        return GetCategoryNameByIdUseCaseImpl(repository = repository)
    }
}