package com.example.currencyexchangeapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.currencyexchangeapp.databinding.ActivityMainBinding
import com.example.currencyexchangeapp.util.Constants
import com.example.currencyexchangeapp.viewmodel.CurrencyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var currencyViewModel: CurrencyViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        setupNumericButtons()
        setupClearButton()

        binding.btnSwap.setOnClickListener {
            swapCurrencies()
        }

        openCurrencyListActivityFrom()
        openCurrencyListActivityTo()
        observeConversionResult()

    }

    private fun swapCurrencies() {

        val tempCurrencyName1 = binding.textCurrency1.text.toString()
        val tempCurrencyValue1 = binding.textValue1.text.toString()
        val tempCurrencyFlag1 = binding.imageCurrency1.drawable //drawable?

        binding.textCurrency1.text = binding.textCurrency2.text
        binding.textValue1.text = binding.textValue2.text
        binding.imageCurrency1.setImageDrawable(binding.imageCurrency2.drawable)

        binding.textCurrency2.text = tempCurrencyName1
        binding.textValue2.text = tempCurrencyValue1
        binding.imageCurrency2.setImageDrawable(tempCurrencyFlag1)

    }

    private var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            if (data?.hasExtra(Constants.CURRENCY_CODE) == true) {
                val currencyCode = data.getStringExtra(Constants.CURRENCY_CODE) ?: return@registerForActivityResult
                val openCurrencyListCode = data.getIntExtra("openCurrencyListCode", 0)

                if(openCurrencyListCode > 0) {
                    when (openCurrencyListCode) {
                        10 -> {
                            binding.textCurrency1.text = currencyCode
                            currencyViewModel.onCurrencyFromChanged(currencyCode)
                        }
                        20 -> {
                            binding.textCurrency2.text = currencyCode
                            currencyViewModel.onCurrencyToChanged(currencyCode)
                        }
                    }
                }
            }
        }
    }

    private fun observeConversionResult() {

        currencyViewModel.conversionValue.observe(this) { conversionValue ->
            binding.textValue2.text = conversionValue
        }

        listOf(binding.textCurrency1, binding.textCurrency2, binding.textValue1).forEach { textView ->
            textView.addTextChangedListener {
                val currencyFrom = binding.textCurrency1.text.toString()
                val currencyTo = binding.textCurrency2.text.toString()
                val amount = binding.textValue1.text.toString().toIntOrNull() ?: 0
                currencyViewModel.onCurrencyChanged(currencyFrom, currencyTo, amount)
            }
        }
    }

    private fun openCurrencyListActivityFrom() {
        binding.clCurrency1.setOnClickListener {
            val intent = Intent(this, CurrencyListActivity::class.java)
            intent.putExtra("openCurrencyListCode", 10)
            startForResult.launch(intent)
        }
    }

    private fun openCurrencyListActivityTo() {
        binding.clCurrency2.setOnClickListener {
            val intent = Intent(this, CurrencyListActivity::class.java)
            intent.putExtra("openCurrencyListCode", 20)
            startForResult.launch(intent)
        }
    }

    private fun setupNumericButtons() {
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

        buttons.forEach { button ->
            button.setOnClickListener {
                val currentText = binding.textValue1.text.toString()
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
            binding.textValue2.text = "0"
        }
    }
}