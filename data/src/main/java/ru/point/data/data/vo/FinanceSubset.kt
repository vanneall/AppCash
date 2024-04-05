package ru.point.data.data.vo

import androidx.room.ColumnInfo
import ru.point.data.data.entities.FolderIcon

data class FinanceSubset(
    @ColumnInfo("id")
    val id: Long? = null,
    @ColumnInfo("name")
    val folderName: String? = null,
    @ColumnInfo("icon")
    val icon: FolderIcon? = null,
    @ColumnInfo("price")
    val price: Int? = null
) {
    fun isValuesNull(): Boolean {
        return id == null && folderName == null && icon == null && price == null
    }
}