package com.example.currencyexchangeapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchangeapp.databinding.ItemListBinding
import com.example.currencyexchangeapp.model.model.CurrencyItemModel

class CurrencyAdapter(
    private var currencies: List<CurrencyItemModel>
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        //Vincular os dados de um item da lista ao layout correspondente
        fun bind(currency: CurrencyItemModel) {
            binding.textNameCurrency.text = currency.name
            binding.textCodeCurrency.text = currency.code
//
//            // Carregar a bandeira usando Picasso
//            val flagUrl = countryFlags[currency.code]
//            if (!flagUrl.isNullOrEmpty()) {
//                Picasso.get().load(flagUrl).into(binding.imageFlag)
//            }
        }
    }

    //Criar a visualização de uma nova view para um item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context) //Criar um objeto do tipo LayoutInflater
        val itemView = ItemListBinding.inflate(inflater, parent, false) //Converter o item_list em um objeto o tipo view (???)
        return CurrencyViewHolder(itemView)
    }

    //Fazer a vinculação dos dados de um item da lista, ao viewHolder correspondente
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position]) //Passar o item atual da lista (currencyList[position]) para o viewHolder
    }

    //Recuperar a quantidade de itens do RecyclerView
    override fun getItemCount(): Int {
        return currencies.size
    }

    // Atualizar a lista
    fun submitList(newList: List<CurrencyItemModel>) {
        currencies = newList
        notifyDataSetChanged()
    }
}