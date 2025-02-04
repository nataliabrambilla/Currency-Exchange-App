package com.example.currencyexchangeapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyexchangeapp.databinding.ActivityCurrencyListBinding
import com.example.currencyexchangeapp.util.Constants
import com.example.currencyexchangeapp.viewmodel.CurrencyViewModel

class CurrencyListActivity : AppCompatActivity(), CurrencyAdapter.OnCurrencyClickListener {

    private val binding by lazy {
        ActivityCurrencyListBinding.inflate(layoutInflater)
    }

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Instanciando o ViewModel, através do ViewModelProvider
        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)

        // Configurar a RecyclerView
        setupRecyclerView()

        // Observar as atualizações do LiveData para a lista de moedas
        observeCurrencyList()

        //Voltar para a MainActivity
        binding.btnBack.setOnClickListener {
            finish()
        }

        //Carregar a lista de moedas
        currencyViewModel.fetchCurrencyList()

    }

    private fun setupRecyclerView() {
        currencyAdapter = CurrencyAdapter(emptyList(), this) // Inicializar o adapter com uma lista vazia inicialmente
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = currencyAdapter
    }

    private fun observeCurrencyList() {
        currencyViewModel.currencyList.observe(this) { currencyModel ->
            val sortedCurrencyList = currencyModel.listItem.sortedBy {
                it.listItemName
            }
            currencyAdapter.updateCurrencyList(sortedCurrencyList)
        }
    }

    override fun onCurrencyClick(currencyCode: String) {
        val i = Intent()
        i.putExtra(Constants.CURRENCY_CODE, currencyCode)
        setResult(Activity.RESULT_OK, i)
        finish()
    }
}
