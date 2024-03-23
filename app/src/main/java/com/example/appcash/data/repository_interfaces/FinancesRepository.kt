package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import kotlinx.coroutines.flow.Flow

interface FinancesRepository {
    fun getFinancesByMonthId(id: String): Flow<Map<Finance, Category>>
    fun getFinancesByFolderId(id: String): Flow<Map<Category, Int>>
    fun insertFinance(value: Finance)
}