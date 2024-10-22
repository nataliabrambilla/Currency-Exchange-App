package com.example.currencyexchangeapp.view.viewholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyexchangeapp.adapter.CurrencyAdapter
import com.example.currencyexchangeapp.databinding.ActivityCurrencyListBinding
import com.example.currencyexchangeapp.view.viewmodel.CurrencyListViewModel
import com.example.currencyexchangeapp.Result

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

        // Carregar a lista de moedas
        currencyListViewModel.fetchCurrencyList()
    }

    private fun setupRecyclerView() {

        // Inicializar o adapter com uma lista vazia inicialmente
        currencyAdapter = CurrencyAdapter(emptyList()) // Inicialmente vazio

        binding.rvList.adapter = currencyAdapter
        binding.rvList.layoutManager = LinearLayoutManager(this)

        /*binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@CurrencyListActivity)
            adapter = currencyAdapter
        }*/
    }

    private fun observeCurrencyList() {
        currencyListViewModel.currencyList.observe(this, Observer { result ->

            if (result is Result.Success) {
                currencyAdapter.submitList(result.value) // Atualiza o adapter com a nova lista de moedas
            } else if (result is Result.Error) {
                Toast.makeText(this, result.message, Toast.LENGTH_LONG).show() // Exibe mensagem de erro
            }
        })
    }
}