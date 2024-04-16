package ru.point.data.data.datasource.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.data.data.entity.subset.FinanceSubset

interface FinancesRepository {
    fun getFinancesByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset>>
    fun getFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>>

    fun insertFinance(value: Finance)
    fun getAllFinances(): Flow<List<FinanceSubset>>
    fun getFinancesSum(): Flow<Int?>
}