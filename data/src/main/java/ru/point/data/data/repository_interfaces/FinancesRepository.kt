package ru.point.data.data.repository_interfaces

import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import kotlinx.coroutines.flow.Flow

interface FinancesRepository {
    fun getFinancesByMonthId(id: String): Flow<Map<Finance, Category>>
    fun getFinancesByFolderId(id: String): Flow<Map<Category, Int>>
    fun insertFinance(value: Finance)
}