package com.example.currencyexchangeapp.service.repository

import com.example.currencyexchangeapp.service.model.CurrencyConversionResponse
import com.example.currencyexchangeapp.service.model.CurrencyListModel
import com.example.currencyexchangeapp.Result
import com.example.currencyexchangeapp.service.repository.remote.RetrofitHelper
import java.util.Currency

class CurrencyRepository {

    private val api = RetrofitHelper.retrofitGetgeoapi

    suspend fun getCurrencyList(): Result<CurrencyListModel> {
        return try {
            val response = api.getCurrencyList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error("Body vazio")
                }
            } else {
                Result.Error("Falha na resposta da API")
            }
        } catch (e: Exception) {
            Result.Error("Erro: ${e.message}")
        }
    }

    suspend fun getCurrencyConversion(
        from: String,
        to: String,
        amount: Int,
    ): Result<CurrencyConversionResponse> {
        val result = try {
            api.getCurrencyConversion(
                from = from,
                to = to,
                amount = amount,
            )
        } catch (e: Exception) {
            return Result.Error(e.message.orEmpty())
        }

        val body = result.body()

        return if (result.isSuccessful && body != null) {
            Result.Success(body)
        } else {
            Result.Error("Result not successful")
        }
    }
}

/*val result = try {
    api.getCurrencyList()
} catch (e: Exception) {
    return Result.Error(e.message.orEmpty())
}

val body = result.body()

return if (result.isSuccessful && body != null) {
    Result.Success(body)
} else {
    Result.Error("Result not successful")
}*/


