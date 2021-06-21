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

    fun updateListProducts(lista: List<Product>){
        listItens = lista
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
        val recentItems = listItens[position]

        holder.tvNumberItem.text = recentItems.id.toString()
        holder.tvCodeItem.text = recentItems.bar_code
//        holder.tvQtd.text = recentItems.total_quantity.toString()
        holder.tvNameItem.text = recentItems.product_name
        holder.tvTotalValue.text = (recentItems.cost_per_item.toString().toDouble()).toString()
        holder.tvUnitValue.text = recentItems.cost_per_item.toString()

        if(recentItems.cost_per_item <= 0){
            holder.tvUnitValue.text = "GRÁTIS"
        }else {
            holder.tvUnitValue.text = "R$ ${String.format("%.2f", recentItems.cost_per_item)}"
        }
    }

    //inner class
    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvNumberItem = view.findViewById<TextView>(R.id.product_number)
        val tvCodeItem = view.findViewById<TextView>(R.id.product_code)
        val tvQtd = view.findViewById<TextView>(R.id.product_quantity)
        val tvNameItem = view.findViewById<TextView>(R.id.product_name)
        val tvUnitValue = view.findViewById<TextView>(R.id.product_un_value)
        val tvTotalValue = view.findViewById<TextView>(R.id.product_total_price)
    }
}