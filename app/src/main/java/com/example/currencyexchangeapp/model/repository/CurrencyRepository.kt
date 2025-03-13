package com.example.currencyexchangeapp.model.repository

import com.example.currencyexchangeapp.model.model.CurrencyConversionModel
import com.example.currencyexchangeapp.model.response.CurrencyListResponse
import com.example.currencyexchangeapp.model.model.CurrencyListModel
import com.example.currencyexchangeapp.model.model.CurrencyListItemModel
import com.example.currencyexchangeapp.model.response.CurrencyConversionResponse

class CurrencyRepository {

    private val remote = RetrofitHelper.currencyAPI
    //private val restcountriesApi = RetrofitHelper.retrofitRestcountries

    private suspend fun getCurrencyList(): CurrencyListResponse {
        val response = remote.getCurrencyList()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }

    suspend fun getCurrenciesModel(): CurrencyListModel {
        val currencyListResult = getCurrencyList()
        val currenciesItemList = mutableListOf<CurrencyListItemModel>()

        for (entry in currencyListResult.currencies) {
            val newListCurrency = CurrencyListItemModel(listItemCode = entry.key, listItemName = entry.value)

            currenciesItemList.add(newListCurrency)
        }

        return CurrencyListModel(listItem = currenciesItemList.toList())
    }

    suspend fun getCurrencyConversion(
        from: String,
        to: String,
        amount: Int,
    ): CurrencyConversionResponse {
        val response = remote.getCurrencyConversion(from = from, to = to, amount = amount)
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }

    suspend fun getCurrencyConversionModel(
        from: String,
        to: String,
        amount: Int
    ): CurrencyConversionModel {

        val currencyConversionResult = getCurrencyConversion(from = from, to = to, amount = amount)
        val currencyRatesList = currencyConversionResult.rates.values.toList()

        val conversionItemCode = currencyConversionResult.rates.keys.toString()
        val conversionFinalValue = currencyRatesList[0].rate_for_amount

        return CurrencyConversionModel(
            conversionItemCode = conversionItemCode,
            conversionFinalValue = conversionFinalValue
        )
    }
}