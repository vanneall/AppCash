package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.domain.finance.implementations.GetAllFinancesUseCaseImpl
import ru.point.domain.finance.implementations.GetExpenseFinanceUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesByMonthAndOpenModeUseCaseImpl
import ru.point.domain.finance.implementations.GetIncomeFinancesByYearMonthUseCaseImpl
import ru.point.domain.finance.implementations.GetFinancesSumUseCaseImpl
import ru.point.domain.finance.implementations.InsertFinanceUseCaseImpl
import ru.point.domain.finance.interfaces.GetAllFinancesUseCase
import ru.point.domain.finance.interfaces.GetExpenseFinanceUseCase
import ru.point.domain.finance.interfaces.GetFinancesByMonthUseCase
import ru.point.domain.finance.interfaces.GetFinancesByYearMonthUseCase
import ru.point.domain.finance.interfaces.GetFinancesSumUseCase
import ru.point.domain.finance.interfaces.InsertFinanceUseCase

@Module
@InstallIn(ViewModelComponent::class)
class FinanceModule {
    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByYearMonthUseCase(repository: FinancesRepository): GetFinancesByYearMonthUseCase {
        return GetIncomeFinancesByYearMonthUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetExpenseByYearMonthUseCase(repository: FinancesRepository): GetExpenseFinanceUseCase {
        return GetExpenseFinanceUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByFolderUseCase(repository: FinancesRepository): GetFinancesByMonthUseCase {
        return GetFinancesByMonthAndOpenModeUseCaseImpl(repository = repository)
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