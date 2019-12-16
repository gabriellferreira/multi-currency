package br.com.gabriellferreira.multicurrency.presentation.util.extension

import java.text.NumberFormat

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun Double.Companion.parse(string: String): Double {
    return if (string.isNotEmpty()) {
        NumberFormat.getInstance().parse(string)?.toDouble() ?: 0.0
    } else {
        0.0
    }
}