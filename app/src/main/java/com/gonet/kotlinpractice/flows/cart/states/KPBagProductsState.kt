package com.gonet.kotlinpractice.flows.cart.states

import com.gonet.kotlinpractice.general.models.Product

sealed class KPBagProductsState {
    data class Success(val listProducts: List<Product>): KPBagProductsState()
}
