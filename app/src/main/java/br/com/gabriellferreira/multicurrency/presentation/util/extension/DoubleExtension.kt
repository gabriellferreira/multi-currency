package br.com.gabriellferreira.multicurrency.presentation.util.extension

fun Double.format(digits: Int) = "%.${digits}f".format(this)
