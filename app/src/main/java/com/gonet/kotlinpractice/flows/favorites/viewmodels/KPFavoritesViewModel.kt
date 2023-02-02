package com.gonet.kotlinpractice.flows.favorites.viewmodels

import androidx.lifecycle.ViewModel
import com.gonet.kotlinpractice.flows.favorites.states.KPQueryProductsState
import com.gonet.kotlinpractice.storage.KPProductsDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KPFavoritesViewModel(
    private val productDelegate: KPProductsDelegate
): ViewModel() {
    private val _uiState = MutableStateFlow<KPQueryProductsState>(KPQueryProductsState.Success(emptyList()))
    val uiState: StateFlow<KPQueryProductsState> = _uiState

    init {
        val listFav = productDelegate.getFavorites()
        _uiState.value = KPQueryProductsState.Success(listFav)
    }
}