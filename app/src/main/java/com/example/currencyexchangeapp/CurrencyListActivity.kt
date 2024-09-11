package com.example.currencyexchangeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.currencyexchangeapp.databinding.ActivityCurrencyListBinding
import com.example.currencyexchangeapp.databinding.ActivityMainBinding

class CurrencyListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCurrencyListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Voltar para a MainActivity
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}