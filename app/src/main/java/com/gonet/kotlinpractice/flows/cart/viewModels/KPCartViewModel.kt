package com.gonet.kotlinpractice.flows.cart.viewModels

import androidx.lifecycle.ViewModel
import com.gonet.kotlinpractice.flows.cart.states.KPBagProductsState
import com.gonet.kotlinpractice.storage.KPProductsDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KPCartViewModel(
    private val productDelegate: KPProductsDelegate
): ViewModel() {
    private val _uiState = MutableStateFlow<KPBagProductsState>(KPBagProductsState.Success(emptyList()))
    val uiState: StateFlow<KPBagProductsState> = _uiState

    init {
        val listFav = productDelegate.getListBag()
        _uiState.value = KPBagProductsState.Success(listFav)
    }

    fun removeProduct(id: Int) {
        productDelegate.removeFromBag(id)
    }
}