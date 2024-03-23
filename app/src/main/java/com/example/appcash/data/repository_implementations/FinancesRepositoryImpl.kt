package com.example.appcash.data.repository_implementations

import com.example.appcash.data.dao.FinanceDao
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import com.example.appcash.data.repository_interfaces.FinancesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FinancesRepositoryImpl @Inject constructor(
    private val financeDao: FinanceDao
) : FinancesRepository {
    override fun getFinancesByMonthId(id: String): Flow<Map<Finance, Category>> {
        return financeDao.readByMonthId(id)
    }

    override fun getFinancesByFolderId(id: String): Flow<Map<Category, Int>> {
        return financeDao.readByFolder(id)
    }

    override fun insertFinance(value: Finance) {
        financeDao.create(value)
    }
}