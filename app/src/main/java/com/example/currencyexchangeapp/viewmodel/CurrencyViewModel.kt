package com.example.currencyexchangeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencyexchangeapp.model.model.CurrencyListModel
import com.example.currencyexchangeapp.model.repository.CurrencyRepository
import com.example.currencyexchangeapp.model.response.CurrencyConversionResponse
import kotlinx.coroutines.launch

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    private var currencyFrom: String? = null
    private var currencyTo: String? = null

    private val currencyRepository = CurrencyRepository() //Cria a instancia do CurrencyRepository, para interagir com o banco de dados.

    private val _currencyList = MutableLiveData<CurrencyListModel>() //MutableLiveData -> Classe de dados observável e mutável apenas dentro do ViewModel. <CurrenciesModel> -> Tipo de dados que será armazenado dentro do MutableLiveData.
    val currencyList: LiveData<CurrencyListModel> = _currencyList //LiveData -> Classe de dados de somente leitura. A currencyList expõe o LiveData para o resto do aplicativo.

    fun onCurrencyFromChanged(value: String) {
        currencyFrom = value
    }

    fun onCurrencyToChanged(value: String) {
        currencyTo = value
    }

    fun fetchCurrencyList() {
        viewModelScope.launch {
            try {
                val resultCurrencyList = currencyRepository.getCurrenciesModel() //Chama o repositório para buscar o CurrencyListViewModel
                _currencyList.postValue(resultCurrencyList)

            } catch (e: Exception) {
                println("NATALIA -> Erro currencyRepository.getCurrenciesModel() ")
                // tratar erro
            }
        }
    }

    fun fetchCurrencyConversion() {
        if (currencyTo == null && currencyFrom == null) return
        viewModelScope.launch {
            try {
                var resultCurrencyConversion = currencyRepository.getCurrencyConversionModel(
                    from = currencyFrom.orEmpty(),
                    to = currencyTo.orEmpty(),
                    amount = 1,
                ) //Trocar para pegar da tela

            } catch (e: Exception) {
                println("NATALIA -> Erro currencyRepository.getCurrencyConversion() ")
                //tratar erro
            }
        }
    }
}
