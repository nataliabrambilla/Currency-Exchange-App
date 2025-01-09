package com.example.currencyexchangeapp.model.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        val retrofitGetgeoapi = Retrofit.Builder()
            .baseUrl("https://api.getgeoapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyService::class.java)

        val retrofitRestcountries = Retrofit.Builder()
            .baseUrl("https://restcountries.com/") //VERIFICAR URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlagService::class.java)

        /*private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitHelper::class.java) {
                    val retrofitGetgeoapi = Retrofit.Builder()
                        .baseUrl("https://api.getgeoapi.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(CurrencyService::class.java)

                    val retrofitRestcountries = Retrofit.Builder()
                        .baseUrl("https://restcountries.com/") //VERIFICAR URL
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }*/
    }

    /*fun getCurrencyService(): Service<T> {
        return getRetrofitInstance().create(Service::class.java)
    }*/
}
