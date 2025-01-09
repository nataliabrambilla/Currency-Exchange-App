package com.example.currencyexchangeapp.model.model

data class CurrencyConversionResponse(
    val status: String,
    val updated_date: String,
    val base_currency_code: String,
    val amount: Int,
    val base_currency_name: String,
    val rates: Map<String, CurrencyRate>
)

data class CurrencyRate(
    val currency_name: String,
    val rate: String,
    val rate_for_amount: String
)
