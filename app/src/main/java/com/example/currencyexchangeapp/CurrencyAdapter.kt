package com.example.currencyexchangeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.currencyexchangeapp.databinding.ItemListBinding

class CurrencyAdapter(private val currencyList: List<Currency>) :
    Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(private val binding: ItemListBinding) : ViewHolder(binding.root) {


        //Vincular os dados de um item da lista ao layout correspondente
        fun bind(currency: Currency) {

        //Atribuir o valor das propriedades do objeto Currency aos TextViews
            binding.textNameCurrency.text = currency.name
            binding.textCodeCurrency.text = currency.code

            /*// Carrega a imagem da bandeira usando a biblioteca Glide
            Glide.with(binding.root.context)
                .load(currency.flagUrl)
                .into(binding.imageFlag)*/
        }
    }

    //Criar a visualização de uma nova view para um item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {

        //Criar um objeto do tipo LayoutInflater
        val inflater = LayoutInflater.from(parent.context)
        //Converter o item_list em um objeto o tipo view (???)
        val itemView = ItemListBinding.inflate(inflater, parent, false)

        return CurrencyViewHolder(itemView)
    }

    //Fazer a vinculação dos dados de um item da lista, ao viewHolder correspondente
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {

        //Passar o item atual da lista (currencyList[position]) para o viewHolder
        holder.bind(currencyList[position])
    }

    //Recuperar a quantidade de itens do RecyclerView
    override fun getItemCount(): Int {
        return currencyList.size
    }
}