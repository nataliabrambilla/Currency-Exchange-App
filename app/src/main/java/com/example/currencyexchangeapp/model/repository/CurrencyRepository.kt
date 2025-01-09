package com.example.currencyexchangeapp.model.repository

import com.example.currencyexchangeapp.model.model.CurrencyConversionResponse
import com.example.currencyexchangeapp.model.response.CurrencyListResponse
import com.example.currencyexchangeapp.model.model.CurrenciesModel
import com.example.currencyexchangeapp.model.model.CurrencyItemModel
import com.example.currencyexchangeapp.model.response.FlagResponse

class CurrencyRepository {

    private val getgeoApi = RetrofitHelper.retrofitGetgeoapi
    private val restcountriesApi = RetrofitHelper.retrofitRestcountries

    private suspend fun getCurrencyList(): CurrencyListResponse {
        val response = getgeoApi.getCurrencyList()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }

    suspend fun getCurrencyConversion(
        from: String,
        to: String,
        amount: Int,
    ): CurrencyConversionResponse {
        val response = getgeoApi.getCurrencyConversion(from = from, to = to, amount = amount)
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }

    private fun getFlags(): List<FlagResponse> {
        throw Exception("ainda nao implementado")
    }

    suspend fun getCurrenciesModel(): CurrenciesModel {
        val currencyListResult = getCurrencyList()

        val currenciesItemList = mutableListOf<CurrencyItemModel>()

        for (entry in currencyListResult.currencies) {
            val newListCurrency = CurrencyItemModel(
                code = entry.key,
                name = entry.value
            )
            currenciesItemList.add(newListCurrency)
        }

        return CurrenciesModel(
            items = currenciesItemList.toList()
        )
    }
}