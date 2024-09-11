package com.example.currencyexchangeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.currencyexchangeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Criar do binding e inflar o layout
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Abrir a CurrencyListActivity
        binding.clCurrency1.setOnClickListener {
            startActivity(Intent(this, CurrencyListActivity::class.java))
        }

        //Abrir a CurrencyListActivity
        binding.clCurrency2.setOnClickListener {
            startActivity(Intent(this, CurrencyListActivity::class.java))
        }

        //Listener para botões numéricos e .
        val buttons = listOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.buttonDot
        )

        //Atualizar textValue1, ao clicar em button
        buttons.forEach { button ->

            button.setOnClickListener {

                val currentText = binding.textValue1.text.toString()
                val newText = currentText + button.text.toString()

                binding.textValue1.setText(newText)
            }
        }

        //Limpar ao clicar no buttonClear
        binding.buttonClear.setOnClickListener {
            binding.textValue1.setText("")
        }

        //Inverter moedas
        binding.btnSwap.setOnClickListener {
            //3. Inverter as moedas

        }

    }

}