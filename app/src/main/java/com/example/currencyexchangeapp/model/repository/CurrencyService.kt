package com.example.currencyexchangeapp.model.repository

import com.example.currencyexchangeapp.model.model.CurrencyConversionResponse
import com.example.currencyexchangeapp.model.response.CurrencyListResponse
import com.example.currencyexchangeapp.util.Constants.API_KEY
import com.example.currencyexchangeapp.util.Constants.FORMAT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("v2/currency/list")
    suspend fun getCurrencyList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
    ): Response<CurrencyListResponse>

    @GET("v2/currency/convert")
    suspend fun getCurrencyConversion(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
    ): Response<CurrencyConversionResponse>
}