package com.example.task_inovantinovant.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://klinq.com/rest/V1/productdetails/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}