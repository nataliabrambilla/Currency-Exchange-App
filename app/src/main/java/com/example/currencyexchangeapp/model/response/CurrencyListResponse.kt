package com.example.currencyexchangeapp.model.response

data class CurrencyListResponse(
    val currencies: Map<String, String>,
    val status: String
)
