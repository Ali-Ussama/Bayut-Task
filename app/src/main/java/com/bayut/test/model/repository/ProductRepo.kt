package com.bayut.test.model.repository

import com.bayut.test.model.network.api.ProductService
import com.bayut.test.model.network.retrofit.ServiceFactory

object ProductRepo {

    suspend fun getProducts(page: Int?) = ServiceFactory.create(ProductService::class.java).getProducts(page)

}