package com.example.currencyexchangeapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.currencyexchangeapp.databinding.ActivityMainBinding
import com.example.currencyexchangeapp.model.repository.CurrencyRepository
import com.example.currencyexchangeapp.util.Constants
import com.example.currencyexchangeapp.viewmodel.CurrencyViewModel

class MainActivity : AppCompatActivity() {

    var currencyRepository = CurrencyRepository()
    private lateinit var currencyViewModel: CurrencyViewModel

    //Criar do binding e inflar o layout
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    private val register = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) {result ->
//        if (result.resultCode == RESULT_OK) {
//            result.data?.let {
//
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Configurar botões numéricos
        setupNumericButtons()

        //Limpar ao clicar no buttonClear
        setupClearButton()

        //Inverter moedas
        binding.btnSwap.setOnClickListener {
            swapCurrencies()
        }

        //Abrir a CurrencyListActivity
        openCurrencyListActivity1()
        openCurrencyListActivity2()

        //getCurrencyCode1()

        //currencyViewModel.fetchCurrencyConversion()

        /*lifecycleScope.launch {
            var result = currencyRepository.getCurrencyConversionModel(
                from = "USD",
                to = "BRL",
                amount = 10,
            )

            println("natalia: ${result.conversionItemName}, R$ ${result.conversionFinalValue}")
        }*/
    }

    private fun swapCurrencies() {

        //Armazenar temporariamente os dados do clCurrency1
        val tempCurrencyName1 = binding.textCurrency1.text.toString()
        val tempCurrencyValue1 = binding.textValue1.text.toString()
        val tempCurrencyFlag1 = binding.imageCurrency1.drawable //drawable?

        //Trocar os dados entre os dois layouts
        binding.textCurrency1.text = binding.textCurrency2.text
        binding.textValue1.text = binding.textValue2.text
        binding.imageCurrency1.setImageDrawable(binding.imageCurrency2.drawable)

        //Usar os valores temporários para completar a inversão
        binding.textCurrency2.text = tempCurrencyName1
        binding.textValue2.text = tempCurrencyValue1
        binding.imageCurrency2.setImageDrawable(tempCurrencyFlag1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data?.hasExtra(Constants.CURRENCY_CODE) == true) {
                val currencyCode = data.getStringExtra(Constants.CURRENCY_CODE) ?: return
                if (requestCode == 100) {
                    binding.textCurrency1.text = currencyCode
                    currencyViewModel.onCurrencyFromChanged(currencyCode)
                } else if (requestCode == 200) {
                    binding.textCurrency2.text = currencyCode
                    currencyViewModel.onCurrencyToChanged(currencyCode)
                }
            }
        }
    }

    private fun openCurrencyListActivity1() {
        binding.clCurrency1.setOnClickListener {
            startActivityForResult(Intent(this, CurrencyListActivity::class.java), 100)
        }
    }

    private fun openCurrencyListActivity2() {
        binding.clCurrency2.setOnClickListener {
            startActivityForResult(Intent(this, CurrencyListActivity::class.java), 200)
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
        binding.buttonClear.setOnClickListener {
            binding.textValue1.text = "0"
        }
    }

    private fun getCurrencyCode1() {

        val bundle = intent.extras
        val currency = bundle?.getString(Constants.CURRENCY_CODE)
        binding.textCurrency1.text = currency

        /*if () {
            val currencyCode1 = bundle?.getString(Constants.CURRENCY_CODE)
            binding.textCurrency1.text = currencyCode1

        } else {
            val currencyCode2 = bundle?.getString(Constants.CURRENCY_CODE)
            binding.textCurrency2.text = currencyCode2
        }*/
    }

}