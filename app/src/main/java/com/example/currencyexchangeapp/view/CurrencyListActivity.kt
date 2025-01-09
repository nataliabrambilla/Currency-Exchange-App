package com.example.currencyexchangeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyexchangeapp.databinding.ActivityCurrencyListBinding
import com.example.currencyexchangeapp.viewmodel.CurrencyListViewModel

class CurrencyListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCurrencyListBinding.inflate(layoutInflater)
    }

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyListViewModel: CurrencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Inicialize o ViewModel usando ViewModelProvider
        currencyListViewModel = ViewModelProvider(this).get(CurrencyListViewModel::class.java)

        // Configurar a RecyclerView
        setupRecyclerView()

        // Observar as atualizações do LiveData para a lista de moedas
        observeCurrencyList()

        //Voltar para a MainActivity
        binding.btnBack.setOnClickListener {
            finish()
        }

        //Carregar a lista de moedas
        currencyListViewModel.fetchCurrencyList()
    }

    private fun setupRecyclerView() {
        currencyAdapter = CurrencyAdapter(emptyList()) // Inicializar o adapter com uma lista vazia inicialmente

        binding.rvList.adapter = currencyAdapter
        binding.rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun observeCurrencyList() {
        currencyListViewModel.currencyList.observe(this, Observer { result ->

//            if (result is Result.Success) {
//                val sortedCurrencyList = result.value.sortedBy { it.nameCurrencyList } //Coloca a lista em ordem alfabética
//                currencyAdapter.submitList(sortedCurrencyList) // Atualiza o adapter com a nova lista de moedas
//
//            } else if (result is Result.Error) {
//                Toast.makeText(this, result.message, Toast.LENGTH_LONG).show() // Exibe mensagem de erro
//            }
        })
    }
}