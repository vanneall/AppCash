package ru.point.data.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.subset.FinanceCategorySubset
import ru.point.data.data.entity.subset.FinanceSubset
import java.time.LocalDate

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(finance: Finance): Long

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon, fin.date as date " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "WHERE fin.date between :startDate and :endDate "
    )
    fun readByMonthId(startDate: String, endDate: String): Flow<List<FinanceSubset>>

    @Query(
        " SELECT cat.name as name, cat.color as color, cat.icon as icon, SUM(fin.price) as sum " +
                "FROM category cat " +
                "JOIN finance fin " +
                "ON cat.id = fin.category_id " +
                "WHERE fin.date between :startDate and :endDate " +
                "GROUP BY name "
    )
    fun readByFolder(startDate: String, endDate: String): Flow<List<FinanceCategorySubset>>

    @Query(
        "SELECT fin.id as id, fol.name as name, fin.price as price, fol.icon as icon, fin.date as date " +
                "FROM finance fin " +
                "JOIN category fol " +
                "ON fol.id = fin.category_id " +
                "ORDER BY fin.date DESC "
    )
    fun readFinances(): Flow<List<FinanceSubset>>

    @Query("select sum(price) from finance")
    fun readFinancesSum(): Flow<Int?>

    @Query("select min(date) from finance")
    fun readMinDate(): Flow<LocalDate>
}