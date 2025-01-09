package com.example.currencyexchangeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencyexchangeapp.model.model.CurrenciesModel
import com.example.currencyexchangeapp.model.repository.CurrencyRepository
import kotlinx.coroutines.launch

class CurrencyListViewModel(application: Application) : AndroidViewModel(application) {

    private val currencyRepository = CurrencyRepository() //Cria a instancia do CurrencyRepository, para interagir com o banco de dados.

    private val _currencyList = MutableLiveData<CurrenciesModel>() //MutableLiveData -> Classe de dados observável e mutável apenas dentro do ViewModel. Result<List<Currency>> -> Tipo de dados que será armazenado dentro do MutableLiveData.
    val currencyList: LiveData<CurrenciesModel> = _currencyList //LiveData -> Classe de dados de somente leitura. A currencyList expõe o LiveData para o resto do aplicativo.

    fun fetchCurrencyList() {
        viewModelScope.launch {
            try {
                val result = currencyRepository.getCurrenciesModel() //Chama o repositório para buscar o CurrencyListViewModel
                _currencyList.postValue(result)

            } catch (e: Exception) {
                println("NATALIA -> Erro currencyRepository.getCurrenciesModel() ")
                // tratar erro
            }
        }
    }
}
