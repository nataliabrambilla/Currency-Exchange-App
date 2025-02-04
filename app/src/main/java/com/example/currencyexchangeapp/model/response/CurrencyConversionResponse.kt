package com.example.currencyexchangeapp.model.response

data class CurrencyConversionResponse (
    val base_currency_code: String,
    val base_currency_name: String,
    val amount: String,
    val updated_date: String,
    val rates: Map<String, CurrencyRates>,
    val status: String
)
data class CurrencyRates (
    val currency_name: String,
    val rate: String,
    val rate_for_amount: String
)