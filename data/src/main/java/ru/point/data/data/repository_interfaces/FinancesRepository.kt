package ru.point.data.data.repository_interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Finance
import ru.point.data.data.vo.FinanceCategorySubset
import ru.point.data.data.vo.FinanceSubset

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