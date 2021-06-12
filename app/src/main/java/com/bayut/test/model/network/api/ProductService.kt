package com.bayut.test.model.network.api

import com.bayut.test.model.entity.response.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("default/dynamodb-writer")
    suspend fun getProducts(@Query("Page") page: Int?): Response<ProductsResponse>
}