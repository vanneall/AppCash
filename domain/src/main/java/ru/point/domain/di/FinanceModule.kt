package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.domain.finance.implementations.GetAllFinancesUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesByFolderUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesByYearMonthUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesSumUseCaseImpl
import ru.point.domain.finance.implementations.InsertFinanceUseCaseImpl
import ru.point.domain.finance.interfaces.GetAllFinancesUseCase
import ru.point.domain.finance.interfaces.GetFinancesByFolderUseCase
import ru.point.domain.finance.interfaces.GetFinancesByYearMonthUseCase
import ru.point.domain.finance.interfaces.GetFinancesSumUseCase
import ru.point.domain.finance.interfaces.InsertFinanceUseCase

@Module
@InstallIn(ViewModelComponent::class)
class FinanceModule {
    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByYearMonthUseCase(repository: FinancesRepository): GetFinancesByYearMonthUseCase {
        return GetFinancesByYearMonthUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByFolderUseCase(repository: FinancesRepository): GetFinancesByFolderUseCase {
        return GetFinancesByFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFinanceUseCase(repository: FinancesRepository): InsertFinanceUseCase {
        return InsertFinanceUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAllFinancesUseCase(repository: FinancesRepository): GetAllFinancesUseCase {
        return GetAllFinancesUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFinancesSumUseCase(repository: FinancesRepository): GetFinancesSumUseCase {
        return GetFinancesSumUseCaseImpl(repository = repository)
    }
}