package ru.point.data.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.data.data.entity.subset.FinanceSubset

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(finance: Finance): Long

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon, fin.date as date " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "WHERE fin.date between :startDate and :endDate and price > 0"
    )
    fun readIncomeByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset?>>

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon, fin.date as date " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "WHERE fin.date between :startDate and :endDate and price < 0 "
    )
    fun readExpenseByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset?>>

    @Query(
        " SELECT cat.name as name, cat.color as color, cat.icon as icon, SUM(fin.price) as sum " +
                "FROM category cat " +
                "JOIN finance fin " +
                "ON cat.id = fin.category_id " +
                "WHERE fin.price > 0 and fin.date between :startDate and :endDate  " +
                "GROUP BY name "
    )
    fun readByFolderIncome(startDate: String, endDate: String): Flow<List<FinanceCategorySubset?>>

    @Query(
        " SELECT cat.name as name, cat.color as color, cat.icon as icon, SUM(fin.price) as sum " +
                "FROM category cat " +
                "JOIN finance fin " +
                "ON cat.id = fin.category_id " +
                "WHERE fin.price < 0 and fin.date between :startDate and :endDate  " +
                "GROUP BY name "
    )
    fun readByFolderExpense(startDate: String, endDate: String): Flow<List<FinanceCategorySubset?>>

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon, fin.date as date " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "ORDER BY fin.date DESC "
    )
    fun readFinances(): Flow<List<FinanceSubset?>>

    @Query("select sum(price) from finance")
    fun readFinancesSum(): Flow<Int?>

}