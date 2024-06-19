package ru.point.data.data.datasource.repository.implementations.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.remote.api.FinanceApi
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.entity.dto.toFinanceDto
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.data.data.entity.subset.FinanceSubset
import ru.point.data.data.entity.subset.toFinanceSubset
import javax.inject.Inject

class RemoteFinanceRepository @Inject constructor(
    private val api: FinanceApi
) : FinancesRepository {
    override suspend fun getIncomeFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val date = startDate.substringBeforeLast("-")
                val data = api.getIncome(date).execute().body()
                data?.let { finance ->
                    val financeCategory = finance.map {
                        FinanceCategorySubset(
                            name = it.folderName,
                            color = it.color,
                            icon = it.icon,
                            sum = finance.filter { el -> el.folderName == it.folderName }
                                .sumOf { el -> el.price ?: 0 }
                        )
                    }

                    send(financeCategory)
                }

            }
        }
    }

    override suspend fun getExpenseFinancesByFolderId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceCategorySubset>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val date = startDate.substringBeforeLast("-")
                val data = api.getIncome(date).execute().body()
                data?.let { finance ->
                    val financeCategory = finance.map {
                        FinanceCategorySubset(
                            name = it.folderName,
                            color = it.color,
                            icon = it.icon,
                            sum = finance.filter { el -> el.folderName == it.folderName }
                                .sumOf { el -> el.price ?: 0 }
                        )
                    }

                    send(financeCategory)
                }

            }
        }
    }

    override suspend fun getAllFinances(): Flow<List<FinanceSubset>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val res = api.getFinances().execute().body() ?: return@withContext
                send(res.sortedWith { o1, o2 -> o1?.date?.compareTo(o2?.date) ?: 0 }.reversed())
            }
        }
    }

    override suspend fun getFinancesSum(): Flow<Int?> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                send(
                    (api.getFinances().execute().body()
                        ?: return@withContext).sumOf { finance -> finance.price ?: 0 })
            }
        }
    }

    override suspend fun getIncomeFinancesByMonthId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceSubset>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val date = startDate.substringBeforeLast("-")
                send(api.getIncome(date).execute().body()?.map {
                    it.toFinanceSubset()
                } ?: return@withContext)
            }
        }
    }

    override suspend fun getExpenseFinancesByMonthId(
        startDate: String,
        endDate: String
    ): Flow<List<FinanceSubset>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val date = startDate.substringBeforeLast("-")
                send(api.getExpense(date).execute().body()?.map {
                    it.toFinanceSubset()
                } ?: return@withContext)
            }
        }
    }

    override suspend fun insertFinance(value: Finance) {
        withContext(Dispatchers.IO) {
            val valu = value.toFinanceDto()
            api.createFinance(valu).execute()
        }
    }
}