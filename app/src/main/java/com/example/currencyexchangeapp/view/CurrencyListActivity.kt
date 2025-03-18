package com.example.currencyexchangeapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        currencyViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        setupRecyclerView()
        setupSearchFilter()
        observeCurrencyList()
        currencyViewModel.fetchCurrencyList()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        currencyAdapter = CurrencyAdapter(emptyList(), this)
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
        val intent = Intent()
        intent.putExtra(Constants.CURRENCY_CODE, currencyCode)
        intent.putExtra(Constants.OPEN_CURRENCY_LIST_CODE, this.intent.extras?.getInt(Constants.OPEN_CURRENCY_LIST_CODE, 0))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun setupSearchFilter() {
        binding.textSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                currencyAdapter.filter(s.toString())
            }
        })
    }
}
