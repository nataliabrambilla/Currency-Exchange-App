package com.example.currencyexchangeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchangeapp.databinding.ActivityCurrencyListBinding
import com.example.currencyexchangeapp.databinding.ActivityMainBinding

class CurrencyListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCurrencyListBinding.inflate(layoutInflater)
    }

    private lateinit var currencyAdapter: CurrencyAdapter

    //private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Voltar para a MainActivity
        binding.btnBack.setOnClickListener {
            finish()
        }

        //Configurar a lista de moedas
        setupCurrencyList()
    }

    private fun setupCurrencyList() {

        // Exemplo de lista de moedas - essas informações serão carregadas de uma API
        val currencyList = listOf(
            Currency("https://example.com/flags/us.png", "Dollar", "USD"),
            Currency("https://example.com/flags/eu.png", "Euro", "EUR"),
            // Adicione mais moedas aqui
        )

        // Configurar o RecyclerView
        currencyAdapter = CurrencyAdapter(currencyList)

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@CurrencyListActivity)
            adapter = currencyAdapter
        }
    }
}