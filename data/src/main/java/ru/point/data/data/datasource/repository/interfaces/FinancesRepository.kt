package ru.point.data.data.datasource.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.data.data.entity.subset.FinanceSubset

interface FinancesRepository {
    suspend fun getIncomeFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>>

    suspend fun getExpenseFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>>

    suspend fun getAllFinances(): Flow<List<FinanceSubset>>

    suspend fun getFinancesSum(): Flow<Int?>

    suspend fun getIncomeFinancesByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset>>

    suspend fun getExpenseFinancesByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset>>

    suspend fun insertFinance(value: Finance)


}