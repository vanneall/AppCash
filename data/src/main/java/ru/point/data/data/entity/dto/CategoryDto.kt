package ru.point.data.data.entity.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto (
    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "type")
    val discriminator: String,

    @SerializedName(value = "image")
    val icon: String,

    @SerializedName(value = "color")
    val color: Int,

    @SerializedName("")
    val userId: Int = 2
)