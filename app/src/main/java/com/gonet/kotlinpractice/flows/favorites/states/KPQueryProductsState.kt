package com.gonet.kotlinpractice.flows.favorites.states

import com.gonet.kotlinpractice.general.models.Product

sealed class KPQueryProductsState {
    data class Success(val listProducts: List<Product>): KPQueryProductsState()
    object Loading: KPQueryProductsState()
    data class Error(val errorMessage: String): KPQueryProductsState()
}
