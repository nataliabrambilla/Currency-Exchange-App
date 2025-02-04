package com.example.currencyexchangeapp.model.repository

import com.example.currencyexchangeapp.model.response.FlagResponse
import retrofit2.Response
import retrofit2.http.GET

interface FlagService {

    @GET("v3.1/all?fields=currencies,flags")
    suspend fun getCurrencyFlag(): Response<List<FlagResponse>>
}