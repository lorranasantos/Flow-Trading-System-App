package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.ItensCadastrados

class ItensEstoqueAdatpter (

    val listItens: List<ItensCadastrados>,
    val context: Context) : RecyclerView.Adapter<ItensEstoqueAdatpter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.holder_inventory_items, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listItens.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itensRecentes = listItens[position]

        holder.tvNomeItem.text = itensRecentes.nomeItem
        holder.tvQuantidade.text = itensRecentes.quantidade
        holder.tvValor.text = itensRecentes.descValor
    }

    //inner class
    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvNomeItem = view.findViewById<TextView>(R.id.inventory_product_name)
        val tvQuantidade = view.findViewById<TextView>(R.id.inventory_product_quantity)
        val tvValor = view.findViewById<TextView>(R.id.inventory_product_price)
    }
}