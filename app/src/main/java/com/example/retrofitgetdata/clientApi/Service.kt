package com.example.retrofitgetdata.clientApi

import com.example.retrofitgetdata.BlogModel
import com.example.retrofitgetdata.CountryModel
import com.example.retrofitgetdata.DepartmentModel
import com.example.retrofitgetdata.UniversityModel
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("/api/v2/university/?page=3")
    fun getUniList(): Call<UniversityModel>

    @GET("/api/v2/department/")
    fun getDepartmentList(): Call<DepartmentModel>

    @GET("/api/v2/country/")
    fun getCountryList(): Call<CountryModel>

    @GET("/api/v2/blog/")
    fun getBlogList(): Call<BlogModel>

}