package ru.point.data.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.point.data.data.factory.CategoryFactory
import ru.point.data.data.factory.CategoryFactoryImpl
import ru.point.data.data.factory.NoteFactory
import ru.point.data.data.factory.NoteFactoryImpl
import ru.point.data.data.factory.TaskFactory
import ru.point.data.data.factory.TaskFactoryImpl

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    fun provideNoteFactory(): NoteFactory {
        return NoteFactoryImpl()
    }

    @Provides
    fun provideTaskFactory(): TaskFactory {
        return TaskFactoryImpl()
    }

    @Provides
    fun provideCategoryFactory(): CategoryFactory {
        return CategoryFactoryImpl()
    }
}