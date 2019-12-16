package br.com.gabriellferreira.multicurrency.data.model

class CurrencyRatesData(
    val base: String? = null,
    val date: String? = null,
    val rates: Map<String, Double>? = null
)