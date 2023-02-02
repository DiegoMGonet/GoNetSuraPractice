package com.gonet.kotlinpractice.general.extensions

import java.text.DecimalFormat

fun Double.toCurrency(): String {
    val dFormat = DecimalFormat("$ #,###.00")
    return dFormat.format(this)
}

fun Double.toWeight(): String {
    val dFormat = DecimalFormat("#,###.00 Kg")
    return dFormat.format(this)
}