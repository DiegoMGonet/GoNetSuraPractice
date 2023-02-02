package com.gonet.kotlinpractice.flows.description.states

import com.gonet.kotlinpractice.general.models.Product

sealed class KPSummaryState {
    data class ShowProductDetail(val product: Product?): KPSummaryState()
    data class ShowMessage(val message: String): KPSummaryState()
}
