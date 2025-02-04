package com.example.currencyexchangeapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchangeapp.databinding.ItemListBinding
import com.example.currencyexchangeapp.model.model.CurrencyListItemModel

class CurrencyAdapter(private var currencies: List<CurrencyListItemModel>, private val listener: OnCurrencyClickListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        //Vincular os dados de um item da lista ao layout correspondente
        fun bindCurrencyList(currencyListItem: CurrencyListItemModel) {
            binding.textNameCurrency.text = currencyListItem.listItemName
            binding.textCodeCurrency.text = currencyListItem.listItemCode

            binding.currencySelection.setOnClickListener {
                listener.onCurrencyClick(currencyListItem.listItemCode) // Chama o listener aqui
            }
        }
    }

    //Criar a visualização de uma nova view para um item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context) //Criar um objeto do tipo LayoutInflater
        val itemView = ItemListBinding.inflate(inflater, parent, false) // Inflando o layout item_list
        return CurrencyViewHolder(itemView)
    }

    //Fazer a vinculação dos dados de um item da lista, ao viewHolder correspondente
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindCurrencyList(currencies[position]) //Passar o item atual da lista (currencyList[position]) para o viewHolder
    }

    //Recuperar a quantidade de itens do RecyclerView
    override fun getItemCount(): Int {
        return currencies.size
    }

    // Atualizar a lista
    fun updateCurrencyList(newList: List<CurrencyListItemModel>) {
        currencies = newList
        notifyDataSetChanged()
    }

    interface OnCurrencyClickListener {
        fun onCurrencyClick(currencyCode: String)
    }
}