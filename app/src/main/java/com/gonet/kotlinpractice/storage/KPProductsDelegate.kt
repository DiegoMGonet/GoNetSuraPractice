package com.gonet.kotlinpractice.storage

import android.content.SharedPreferences
import com.gonet.kotlinpractice.general.models.Product

interface KPProductsDelegate {
    fun saveProducts(listProducts: List<Product>)
    fun addToBag(id: Int)
    fun removeFromBag(id: Int)
    fun addToFavorites(id: Int)
    fun removeFromFavorites(id: Int)
    fun getListBag(): List<Product>
    fun getFavorites(): List<Product>
    fun getIdsFavorite(): List<Int>
    fun getProduct(id: Int): Product?
}

class KPProductsDelegateImp(
    private val sharedPref: SharedPreferences
): KPProductsDelegate {
    private val allProducts = arrayListOf<Product>()

    override fun saveProducts(listProducts: List<Product>) {
        allProducts.clear()
        allProducts.addAll(listProducts)
    }

    override fun addToBag(id: Int) {
        val strList = sharedPref.getString("KP_LIST_BAG", "").orEmpty()

        val listIds = if (strList.isEmpty()) {
            listOf("$id")
        } else {
            strList.split("|") + "$id"
        }

        sharedPref.edit().apply {
            putString("KP_LIST_BAG", listIds.joinToString("|"))
            apply()
        }
    }

    override fun removeFromBag(id: Int) {
        val strList = sharedPref.getString("KP_LIST_BAG", "").orEmpty()
        val listIds = strList.split("|")
        val newList = listIds.filter { it != "$id" }

        sharedPref.edit().apply {
            putString("KP_LIST_BAG", newList.joinToString("|"))
            apply()
        }
    }

    override fun addToFavorites(id: Int) {
        val strList = sharedPref.getString("KP_LIST_FAVORITES", "").orEmpty()
        val listIds = if (strList.isEmpty()) {
            listOf("$id")
        } else {
            strList.split("|") + "$id"
        }

        sharedPref.edit().apply {
            putString("KP_LIST_FAVORITES", listIds.joinToString("|"))
            apply()
        }
    }

    override fun removeFromFavorites(id: Int) {
        val strList = sharedPref.getString("KP_LIST_FAVORITES", "").orEmpty()
        val listIds = strList.split("|")
        val newList = listIds.filter { it != "$id" }

        sharedPref.edit().apply {
            putString("KP_LIST_FAVORITES", newList.joinToString("|"))
            apply()
        }
    }

    override fun getListBag(): List<Product> {
        val strList = sharedPref.getString("KP_LIST_BAG", "").orEmpty()
        val listIds = strList.split("|")

        return allProducts.filter { "${it.id}" in listIds }
    }

    override fun getFavorites(): List<Product> {
        val strList = sharedPref.getString("KP_LIST_FAVORITES", "").orEmpty()
        val listIds = strList.split("|")

        return allProducts.filter { "${it.id}" in listIds }
    }

    override fun getIdsFavorite(): List<Int> {
        val strList = sharedPref.getString("KP_LIST_FAVORITES", "").orEmpty()
        val listIds = if(strList.isEmpty()) listOf() else strList.split("|").map {
            it.toInt()
        }

        return listIds
    }

    override fun getProduct(id: Int): Product? {
        return allProducts.firstOrNull { it.id ==  id}
    }
}