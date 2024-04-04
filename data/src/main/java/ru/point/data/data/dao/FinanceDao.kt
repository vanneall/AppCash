package ru.point.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Finance
import ru.point.data.data.vo.FinanceCategorySubset
import ru.point.data.data.vo.FinanceSubset
import ru.point.data.data.vo.LocalDateSubset

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(finance: Finance): Long

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "WHERE fin.date between :startDate and :endDate "
    )
    fun readByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset>>

    @Query(
        " SELECT cat.name as name, cat.color as color, SUM(fin.price) as sum " +
                "FROM category cat " +
                "JOIN finance fin " +
                "ON cat.id = fin.category_id " +
                "WHERE fin.date between :startDate and :endDate "
    )
    fun readByFolder(startDate: String, endDate: String): Flow<List<FinanceCategorySubset>>

    @Query("select * from finance order by date asc limit 1")
    fun readTheOldestMonth(): Flow<LocalDateSubset>

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "ORDER BY fin.date DESC "
    )
    fun readFinances(): Flow<List<FinanceSubset>>

    @Query("select sum(price) from finance")
    fun readFinancesSum(): Flow<Int?>
}