package com.example.retrofitgetdata

import com.google.gson.annotations.SerializedName

data class BlogModel(
    @SerializedName("results")
    val results_: List<BlogResult>
)

data class BlogResult(
    @SerializedName("name")
    val name_: String,
    @SerializedName("thumbnail")
    val thumbnail_: String,
)
