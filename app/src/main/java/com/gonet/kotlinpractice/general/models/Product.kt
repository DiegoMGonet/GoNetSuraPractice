package com.gonet.kotlinpractice.general.models

data class Product(
    val id: Int,
    val name: String,
    val urlImage: String,
    val description: String,
    val price: Double,
    val weight: Double,
    var isFavorite: Boolean = false
)
