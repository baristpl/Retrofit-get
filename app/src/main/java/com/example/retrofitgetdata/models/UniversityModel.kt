package com.example.retrofitgetdata

import com.google.gson.annotations.SerializedName

data class UniversityModel(
   @SerializedName("results")
   val results_: List<UniversityResult>
)

data class UniversityResult(
   @SerializedName("name")
   val name_: String,
   @SerializedName("province")
   val province_: Province,
   @SerializedName("thumbnail")
   val thumbnail_: String
)

data class  Province(
   @SerializedName("name")
   val name_: String,
)
