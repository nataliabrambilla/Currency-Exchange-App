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

        openCurrencyListActivity()
        setupNumericButtons()
        setupClearButton()

        //Inverter moedas
        binding.btnSwap.setOnClickListener {
            //3. Inverter as moedas

        }
    }

    private fun openCurrencyListActivity() {

        //A partir de clCurrency1
        binding.clCurrency1.setOnClickListener {
            startActivity(Intent(this, CurrencyListActivity::class.java))
        }

        //A partir de clCurrency2
        binding.clCurrency2.setOnClickListener {
            startActivity(Intent(this, CurrencyListActivity::class.java))
        }
    }

    private fun setupNumericButtons() {

        //Listener para botões numéricos e "."
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

                //Capturar o valor atual do textValue1
                val currentText = binding.textValue1.text.toString()
                //Capturar o valor selecionado do botão clicado
                val buttonText = button.text.toString()

                if (currentText == "0" && buttonText != ".") {
                    binding.textValue1.text = buttonText
                } else {
                    val newText = currentText + buttonText
                    binding.textValue1.text = newText
                }
            }
        }
    }

    private fun setupClearButton() {

        //Limpar ao clicar no buttonClear
        binding.buttonClear.setOnClickListener {
            binding.textValue1.text = "0"
        }
    }

}