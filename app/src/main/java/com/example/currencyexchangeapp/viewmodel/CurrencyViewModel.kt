package com.example.currencyexchangeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyexchangeapp.model.model.CurrencyListModel
import com.example.currencyexchangeapp.model.repository.CurrencyRepository
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {

    private var currencyFrom: String? = null
    private var currencyTo: String? = null

    private val currencyRepository = CurrencyRepository()

    private val _currencyList = MutableLiveData<CurrencyListModel>()
    val currencyList: LiveData<CurrencyListModel> = _currencyList

    private val _conversionValue = MutableLiveData<String>()
    val conversionValue: LiveData<String> = _conversionValue

    fun onCurrencyFromChanged(value: String) {
        currencyFrom = value
    }

    fun onCurrencyToChanged(value: String) {
        currencyTo = value
    }

    fun onCurrencyChanged(from: String, to: String, amount: Int) {
        if (from.isNotEmpty() && to.isNotEmpty() && amount > 0) {
            fetchCurrencyConversion(from, to, amount)
        }
    }

    fun fetchCurrencyList() {
        viewModelScope.launch {
            try {
                val resultCurrencyList = currencyRepository.getCurrencyListModel() //Chama o repositório para buscar o CurrencyListViewModel
                _currencyList.postValue(resultCurrencyList)

            } catch (e: Exception) {
                println("NATALIA -> Erro currencyRepository.getCurrenciesModel() ")
                // tratar erro
            }
        }
    }

    private fun fetchCurrencyConversion(from: String, to: String, amount: Int){
        viewModelScope.launch {
            try {
                val result = currencyRepository.getCurrencyConversionModel(from, to, amount)
                _conversionValue.postValue(result.conversionFinalValue)

            } catch (e: Exception) {
                println("NATALIA -> Erro currencyRepository.getCurrencyConversion() ")
                //tratar erro
            }
        }
    }
}