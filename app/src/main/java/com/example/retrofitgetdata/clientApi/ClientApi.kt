package com.example.retrofitgetdata.clientApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientApi {
    private var baseUrl: String = "https://univerlist.com"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        return retrofit as Retrofit
    }
}