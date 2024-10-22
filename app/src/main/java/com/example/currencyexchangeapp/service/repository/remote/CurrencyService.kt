package com.example.currencyexchangeapp.service.repository.remote

import com.example.currencyexchangeapp.service.model.CurrencyConversionResponse
import com.example.currencyexchangeapp.service.model.CurrencyListModel
import com.example.currencyexchangeapp.service.constants.Constants.API_KEY
import com.example.currencyexchangeapp.service.constants.Constants.FORMAT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("v2/currency/list")
    suspend fun getCurrencyList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
    ): Response<CurrencyListModel>

    @GET("v2/currency/convert")
    suspend fun getCurrencyConversion(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
    ): Response<CurrencyConversionResponse>
}