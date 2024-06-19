package ru.point.data.data.entity.subset

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import ru.point.data.data.entity.entities.FolderIcon
import java.time.LocalDate

data class FinanceSubset(
    @ColumnInfo("id")
    @SerializedName("id")
    val id: Long? = null,
    @ColumnInfo("name")
    @SerializedName("category_name")
    val folderName: String? = null,
    @ColumnInfo("icon")
    @SerializedName("category_photo")
    val icon: FolderIcon? = null,
    @ColumnInfo("price")
    @SerializedName("price")
    val price: Int? = null,
    @ColumnInfo("date")
    @SerializedName("date")
    val date: LocalDate? = null,
)

data class FinanceSubsetForRemote(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("category_name")
    val folderName: String? = null,
    @SerializedName("category_photo")
    val icon: FolderIcon? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("date")
    val date: LocalDate? = null,
    @SerializedName("category_color")
    val color: Int = 0
)

fun FinanceSubsetForRemote.toFinanceSubset(): FinanceSubset {
    return FinanceSubset(
        id = id,
        folderName = folderName,
        icon = icon,
        price = price,
        date = date
    )
}
