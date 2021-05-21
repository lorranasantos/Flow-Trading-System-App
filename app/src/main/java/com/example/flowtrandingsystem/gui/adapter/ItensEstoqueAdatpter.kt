package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.ItensCadastrados
import com.example.flowtrandingsystem.gui.model.Produto
import com.example.flowtrandingsystem.gui.ui.DatasheetActivity

class ItensEstoqueAdatpter ( val context: Context) : RecyclerView.Adapter<ItensEstoqueAdatpter.Holder>() {

    var listItens =  emptyList<Produto>()

    fun updateListaProdutos(lista: List<Produto>){
        listItens = lista
        notifyDataSetChanged()
    }

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

        holder.tvNomeItem.text = itensRecentes.product_name
        holder.tvQuantidade.text = itensRecentes.total_quantity.toString()
        holder.tvValor.text = itensRecentes.cost_per_item.toString()

        if(itensRecentes.cost_per_item <= 0){
            holder.tvValor.text = "GRÁTIS"
        }else {
            holder.tvValor.text = "R$ ${String.format("%.2f", itensRecentes.cost_per_item)}"
        }

        holder.cardInventoryItems.setOnClickListener{
            val intent = Intent(context, DatasheetActivity::class.java)
            context.startActivity(intent)
        }
    }

    //inner class
    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvNomeItem = view.findViewById<TextView>(R.id.inventory_product_name)
        val tvQuantidade = view.findViewById<TextView>(R.id.inventory_product_quantity)
        val tvValor = view.findViewById<TextView>(R.id.inventory_product_price)
        val cardInventoryItems = view.findViewById<CardView>(R.id.card_produto_inventory)
    }
}