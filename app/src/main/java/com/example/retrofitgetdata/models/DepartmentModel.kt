package com.example.retrofitgetdata

import com.google.gson.annotations.SerializedName

data class DepartmentModel(
    @SerializedName("results")
    val results_: List<DepartmentResult>
)

data class DepartmentResult(
    @SerializedName("name")
    val name_: String,
)

