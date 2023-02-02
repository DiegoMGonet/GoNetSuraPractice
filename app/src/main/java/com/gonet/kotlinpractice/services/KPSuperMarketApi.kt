package com.gonet.kotlinpractice.services

import com.gonet.kotlinpractice.general.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface KPSuperMarketApi {
    @GET("/products")
    suspend fun getAllProducts(): Response<List<Product>>
}