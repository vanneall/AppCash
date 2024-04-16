package ru.point.data.data.entity.subset

import androidx.room.ColumnInfo
import ru.point.data.data.entity.entities.FolderIcon

data class FinanceCategorySubset(
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("color")
    val color: Int? = null,
    @ColumnInfo("icon")
    val icon: FolderIcon? = null,
    @ColumnInfo("sum")
    val sum: Int? = null
) {
    fun isValuesNull(): Boolean {
        return name == null && color == null && sum == null && icon == null
    }
}
