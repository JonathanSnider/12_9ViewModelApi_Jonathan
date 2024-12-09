package com.example.apilearning.data.model


import com.google.gson.annotations.SerializedName

data class DrinkDetailModel(
    @SerializedName("drinks")
    val drinks: List<DrinkModel?>? = listOf()
)