package com.example.currencyexchangeapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchangeapp.databinding.ItemListBinding
import com.example.currencyexchangeapp.model.model.CurrencyListItemModel

class CurrencyAdapter(private var currencies: List<CurrencyListItemModel>, private val listener: OnCurrencyClickListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var originalList: List<CurrencyListItemModel> = currencies.toList()

    inner class CurrencyViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCurrencyList(currencyListItem: CurrencyListItemModel) {
            binding.textNameCurrency.text = currencyListItem.listItemName
            binding.textCodeCurrency.text = currencyListItem.listItemCode

            binding.currencySelection.setOnClickListener {
                listener.onCurrencyClick(currencyListItem.listItemCode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemListBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindCurrencyList(currencies[position])
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun updateCurrencyList(newList: List<CurrencyListItemModel>) {
        originalList = newList.toList()
        currencies = newList
        notifyDataSetChanged()
    }

    interface OnCurrencyClickListener {
        fun onCurrencyClick(currencyCode: String)
    }

    fun filter(text: String) {
        currencies = if (text.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.listItemName.startsWith(text, ignoreCase = true) || it.listItemCode.startsWith(text, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}