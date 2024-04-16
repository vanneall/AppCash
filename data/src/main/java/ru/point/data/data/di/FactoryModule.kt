package ru.point.data.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.point.data.data.factory.NoteFactory
import ru.point.data.data.factory.NoteFactoryImpl

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    fun provideNoteFactory(): NoteFactory {
        return NoteFactoryImpl()
    }

}