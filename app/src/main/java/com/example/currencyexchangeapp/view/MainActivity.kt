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
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var currencyViewModel: CurrencyViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        currencyViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        setupNumericButtons()
        setupClearButton()
        binding.btnSwap.setOnClickListener {
            swapCurrencies()
        }
        openCurrencyListActivityFrom()
        openCurrencyListActivityTo()
        observeConversionResult()

    }

    private var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            if (data?.hasExtra(Constants.CURRENCY_CODE) == true) {
                val currencyCode = data.getStringExtra(Constants.CURRENCY_CODE) ?: return@registerForActivityResult
                val openCurrencyListCode = data.getIntExtra(Constants.OPEN_CURRENCY_LIST_CODE, 0)
                if(openCurrencyListCode > 0) {
                    when (openCurrencyListCode) {
                        Constants.CURRENCY_LIST_ACTIVITY_FROM_CODE -> {
                            binding.textCurrency1.text = currencyCode
                            currencyViewModel.onCurrencyFromChanged(currencyCode)
                        }
                        Constants.CURRENCY_LIST_ACTIVITY_TO_CODE -> {
                            binding.textCurrency2.text = currencyCode
                            currencyViewModel.onCurrencyToChanged(currencyCode)
                        }
                    }
                }
            }
        }
    }

    private fun openCurrencyListActivityFrom() {
        binding.clCurrency1.setOnClickListener {
            val intent = Intent(this, CurrencyListActivity::class.java)
            intent.putExtra(Constants.OPEN_CURRENCY_LIST_CODE, Constants.CURRENCY_LIST_ACTIVITY_FROM_CODE)
            startForResult.launch(intent)
        }
    }

    private fun openCurrencyListActivityTo() {
        binding.clCurrency2.setOnClickListener {
            val intent = Intent(this, CurrencyListActivity::class.java)
            intent.putExtra(Constants.OPEN_CURRENCY_LIST_CODE, Constants.CURRENCY_LIST_ACTIVITY_TO_CODE)
            startForResult.launch(intent)
        }
    }

    private fun observeConversionResult() {
        currencyViewModel.conversionValue.observe(this) { conversionValue ->
            binding.textValue2.text = String.format(Locale.getDefault(),"%.2f", conversionValue.toDoubleOrNull() ?: 0.0)
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

                if (currentText == Constants.ZERO && buttonText != Constants.DOT) {
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
            binding.textValue1.text = Constants.ZERO
            binding.textValue2.text = Constants.ZERO
        }
    }
}