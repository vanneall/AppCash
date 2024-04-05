package ru.point.data.data.repository_implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.point.data.data.dao.FinanceDao
import ru.point.data.data.entities.Finance
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.data.data.vo.FinanceCategorySubset
import ru.point.data.data.vo.FinanceSubset
import javax.inject.Inject

class FinancesRepositoryImpl @Inject constructor(
    private val financeDao: FinanceDao
) : FinancesRepository {
    override fun getFinancesByMonthId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceSubset>> {
        return financeDao.readByMonthId(startDate, endDate).map { it.filterNot { item -> item.isValuesNull() } }
    }

    override fun getFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>> {
        return financeDao.readByFolder(startDate, endDate).map { it.filterNot { item -> item.isValuesNull() } }
    }

    override fun insertFinance(value: Finance) {
        financeDao.create(value)
    }

    override fun getAllFinances(): Flow<List<FinanceSubset>> {
        return financeDao.readFinances().map { it.filterNot { item -> item.isValuesNull() } }
    }

    override fun getFinancesSum(): Flow<Int?> {
        return financeDao.readFinancesSum()
    }
}