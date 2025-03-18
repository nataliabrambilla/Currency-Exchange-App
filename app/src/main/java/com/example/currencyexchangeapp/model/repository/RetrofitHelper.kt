package com.example.currencyexchangeapp.model.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        val currencyAPI = Retrofit.Builder()
            .baseUrl("https://api.getgeoapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyService::class.java)
    }
}
