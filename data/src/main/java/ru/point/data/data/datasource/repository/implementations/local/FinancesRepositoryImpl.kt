package ru.point.data.data.datasource.repository.implementations.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.local.dao.FinanceDao
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.data.data.entity.subset.FinanceSubset
import javax.inject.Inject

class FinancesRepositoryImpl @Inject constructor(
    private val financeDao: FinanceDao
) : FinancesRepository {
    override suspend fun getIncomeFinancesByMonthId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceSubset>> {
        return withContext(Dispatchers.IO) {
            financeDao.readIncomeByMonthId(startDate, endDate).map { it.filterNotNull() }
        }
    }

    override suspend fun getIncomeFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>> {
        return withContext(Dispatchers.IO) {
            financeDao.readByFolderIncome(startDate, endDate).map { it.filterNotNull() }
        }
    }

    override suspend fun insertFinance(value: Finance) {
        withContext(Dispatchers.IO) {
            financeDao.create(value)
        }
    }

    override suspend fun getAllFinances(): Flow<List<FinanceSubset>> {
        return withContext(Dispatchers.IO) {
            financeDao.readFinances().map { it.filterNotNull() }
        }
    }

    override suspend fun getFinancesSum(): Flow<Int?> {
        return withContext(Dispatchers.IO) {
            financeDao.readFinancesSum()
        }
    }

    override suspend fun getExpenseFinancesByMonthId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceSubset>> {
        return withContext(Dispatchers.IO) {
            financeDao.readExpenseByMonthId(startDate, endDate).map { it.filterNotNull() }
        }
    }

    override suspend fun getExpenseFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>> {
        return withContext(Dispatchers.IO) {
            financeDao.readByFolderExpense(startDate, endDate).map { it.filterNotNull() }
        }
    }
}