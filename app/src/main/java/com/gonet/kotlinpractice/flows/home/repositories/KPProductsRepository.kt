package com.gonet.kotlinpractice.flows.home.repositories

import com.gonet.kotlinpractice.general.models.Product
import com.gonet.kotlinpractice.services.KPSuperMarketApi

class KPProductsRepository(private val superMarketApi: KPSuperMarketApi) {
    suspend fun getAllProducts(): Pair<List<Product>, String> {
        val response = superMarketApi.getAllProducts()
        return if (response.isSuccessful) {
            Pair(response.body() ?: emptyList(), "")
        } else {
            Pair(emptyList(), response.message())
        }
    }
}