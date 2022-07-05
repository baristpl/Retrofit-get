package com.example.retrofitgetdata

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("results")
    val results_: List<CountryResult>
)

data class CountryResult(
    @SerializedName("name")
    val name_: String,
    @SerializedName("image")
    val image_: String,
)


