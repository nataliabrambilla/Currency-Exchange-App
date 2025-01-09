package com.example.currencyexchangeapp.model.response

data class FlagResponse(
    val flags: Map<String, String>,
    val currencies: Map<String, FlagCurrencyInfoResponse>
)

data class FlagCurrencyInfoResponse(
    val name: String,
    val symbol: String
)