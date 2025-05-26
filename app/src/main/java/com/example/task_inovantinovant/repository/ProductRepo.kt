package com.example.task_inovantinovant.repository

import com.example.task_inovantinovant.api.ApiInterface
import com.example.task_inovantinovant.models.ProductDetailResponse

class ProductRepo(private val apiInterface: ApiInterface) {

    suspend fun getProductDetails(): ProductDetailResponse {
        val response = apiInterface.getProductDetails()
        return response.body() ?: ProductDetailResponse()
    }
}
