package ru.point.data.data.entity.dto

import com.google.gson.annotations.SerializedName
import ru.point.data.data.entity.entities.Finance
import java.time.LocalDate

data class FinanceDto(
    @SerializedName("price")
    val price: Int,
    @SerializedName("folder_id")
    val folderId: Long,
    @SerializedName("date")
    val date: String = LocalDate.now().toString(),
    @SerializedName("currency")
    val currency: String = "RUR"
)

fun Finance.toFinanceDto(): FinanceDto {
    return FinanceDto(
        price = this.price,
        folderId = this.folderId
    )
}