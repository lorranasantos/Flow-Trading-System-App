package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.ui.DatasheetActivity

class BarCodeAdapter (val context: Context) : RecyclerView.Adapter<BarCodeAdapter.Holder>() {

    var listItens =  emptyList<Product>()

    fun updateListaProdutos(lista: Product){
        listItens = listOf(lista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.holder_list_items_sale, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listItens.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itensRecentes = listItens[position]

        holder.tvNumberItem.text = itensRecentes.id.toString()
        holder.tvCodeItem.text = itensRecentes.bar_code
        holder.tvQuantidade.text = itensRecentes.total_quantity.toString()
        holder.tvNomeItem.text = itensRecentes.product_name
        holder.tvValorUnitario.text = itensRecentes.cost_per_item.toString()


        if(itensRecentes.cost_per_item <= 0){
            holder.tvValorUnitario.text = "GRÁTIS"
        }else {
            holder.tvValorUnitario.text = "R$ ${String.format("%.2f", itensRecentes.cost_per_item)}"
        }

        holder.cardInventoryItems.setOnClickListener{
            val intent = Intent(context, DatasheetActivity::class.java)
            context.startActivity(intent)
        }
    }

    //inner class
    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvNumberItem = view.findViewById<TextView>(R.id.product_number)
        val tvCodeItem = view.findViewById<TextView>(R.id.product_code)
        val tvQuantidade = view.findViewById<TextView>(R.id.product_quantity)
        val tvNomeItem = view.findViewById<TextView>(R.id.product_name)
        val tvValorUnitario = view.findViewById<TextView>(R.id.product_un_value)
        val cardInventoryItems = view.findViewById<CardView>(R.id.product_total_price)
    }
}