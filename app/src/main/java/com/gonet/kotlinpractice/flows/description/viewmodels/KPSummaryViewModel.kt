package com.gonet.kotlinpractice.flows.description.viewmodels

import androidx.lifecycle.ViewModel
import com.gonet.kotlinpractice.flows.description.states.KPSummaryState
import com.gonet.kotlinpractice.storage.KPProductsDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KPSummaryViewModel(
    private val productDelegate: KPProductsDelegate
): ViewModel() {
    private val _uiState = MutableStateFlow<KPSummaryState>(KPSummaryState.ShowProductDetail(null))
    val uiState: StateFlow<KPSummaryState> = _uiState

    fun getProduct(id: Int) {
        val product = productDelegate.getProduct(id)
        _uiState.value = KPSummaryState.ShowProductDetail(product)
    }
}