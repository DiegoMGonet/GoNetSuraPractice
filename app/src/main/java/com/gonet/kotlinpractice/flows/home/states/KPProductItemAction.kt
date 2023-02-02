package com.gonet.kotlinpractice.flows.home.states

sealed class KPProductItemAction {
    data class KPAddToBag(val id: Int): KPProductItemAction()
    data class KPItemClick(val id: Int): KPProductItemAction()
    data class KPChangeFav(val id: Int, val isChecked: Boolean): KPProductItemAction()
}
