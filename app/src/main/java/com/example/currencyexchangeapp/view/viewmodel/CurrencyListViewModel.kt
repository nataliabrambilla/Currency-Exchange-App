package com.example.currencyexchangeapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencyexchangeapp.Result
import com.example.currencyexchangeapp.service.model.Currency
import com.example.currencyexchangeapp.service.repository.CurrencyRepository
import kotlinx.coroutines.launch

class CurrencyListViewModel(application: Application): AndroidViewModel(application) {

    private val currencyRepository = CurrencyRepository()
    private val _currencyList = MutableLiveData<Result<List<Currency>>>()
    val currencyList: LiveData<Result<List<Currency>>> = _currencyList

    fun fetchCurrencyList() {

        viewModelScope.launch {
            try {
                // Chama o repositório para buscar o CurrencyListModel
                val result = currencyRepository.getCurrencyList()

                if (result is Result.Success) {
                    // Converte os dados do Map para uma lista de Currency
                    val currencyList = result.value.currencies.map { (code, name) ->
                        Currency(name, code)
                    }
                    _currencyList.postValue(Result.Success(currencyList))

                } else if (result is Result.Error) {
                    _currencyList.postValue(Result.Error(result.message))
                }

            } catch (e: Exception) {
                    _currencyList.postValue(Result.Error("Erro ao buscar a lista de moedas"))
                }
            }
        }
    }
