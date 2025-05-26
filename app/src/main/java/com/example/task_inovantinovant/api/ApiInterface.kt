package com.example.task_inovantinovant.api

import com.example.task_inovantinovant.models.ProductDetailResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("6701/253620?lang=en&store=KWD")
    suspend fun getProductDetails(): Response<ProductDetailResponse>
}