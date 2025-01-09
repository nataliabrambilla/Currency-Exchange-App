package com.example.currencyexchangeapp.model.model

data class CurrenciesModel(
    val items: List<CurrencyItemModel>
)

data class CurrencyItemModel(
    val code: String,
    val name: String,
)
