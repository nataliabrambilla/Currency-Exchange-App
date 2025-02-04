package com.example.currencyexchangeapp.model.model

data class CurrencyListModel(
    val listItem: List<CurrencyListItemModel>
)

data class CurrencyListItemModel(
    val listItemCode: String,
    val listItemName: String,
)
