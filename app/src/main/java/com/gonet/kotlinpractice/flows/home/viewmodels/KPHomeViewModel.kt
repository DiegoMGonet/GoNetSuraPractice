package com.gonet.kotlinpractice.flows.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonet.kotlinpractice.flows.home.repositories.KPProductsRepository
import com.gonet.kotlinpractice.flows.home.states.KPQueryProductsState
import com.gonet.kotlinpractice.storage.KPProductsDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KPHomeViewModel(
    private val repository: KPProductsRepository,
    private val productDelegate: KPProductsDelegate
): ViewModel() {
    private val _uiState = MutableStateFlow<KPQueryProductsState>(KPQueryProductsState.Success(emptyList()))
    val uiState: StateFlow<KPQueryProductsState> = _uiState

    init {
        viewModelScope.launch {
            repository.getAllProducts().apply {
                val list = first
                val message = second

                if (message.isEmpty()) {
                    val idsFav = productDelegate.getIdsFavorite()
                    val updatedList = list.map {
                        it.copy(isFavorite = it.id in idsFav)
                    }

                    _uiState.value = KPQueryProductsState.Success(updatedList)
                    productDelegate.saveProducts(updatedList)
                } else {
                    _uiState.value = KPQueryProductsState.Error(message)
                }
            }
        }
    }

    fun checkBagItemsNumber(): Int {
        return productDelegate.getListBag().count()
    }

    fun addToBag(id: Int) {
        productDelegate.addToBag(id)
    }

    fun modifyFavoriteById(id: Int, isChecked: Boolean) {
        if(isChecked) {
            productDelegate.addToFavorites(id)
        } else {
            productDelegate.removeFromFavorites(id)
        }
    }
}