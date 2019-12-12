package br.com.gabriellferreira.multicurrency.domain.model

import androidx.annotation.DrawableRes

class Currency(
        val rate: Double,
        val base: String,
        val id: Int,
        val name: String,
        @DrawableRes
        val flagIcon: Int,
        val exponent: Int,
        val code: String
)