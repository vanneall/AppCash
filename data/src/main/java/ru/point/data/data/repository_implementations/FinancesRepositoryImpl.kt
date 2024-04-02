package ru.point.data.data.repository_implementations

import ru.point.data.data.dao.FinanceDao
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import ru.point.data.data.repository_interfaces.FinancesRepository
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