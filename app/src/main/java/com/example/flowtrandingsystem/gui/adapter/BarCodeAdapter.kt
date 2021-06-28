package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.ProductAdapter
import com.example.flowtrandingsystem.gui.ui.PdvActivity
import java.io.Externalizable
import java.io.Serializable
import kotlin.math.log

class BarCodeAdapter (val context: Context) : RecyclerView.Adapter<BarCodeAdapter.Holder>(), Serializable {

    var listItens =  emptyList<ProductAdapter>()

    fun updateListProducts(lista: List<ProductAdapter>){
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

        holder.tvCodeItem.text = recentItems.bar_code
        holder.tvNameItem.text = recentItems.product_name
        holder.tvUnitValue.text = recentItems.cost_per_item.toString()

        holder.tvUnitValue.text = "$${String.format("%.2f", recentItems.cost_per_item)}"

        holder.tvpdvTotalValue.text = "$${String.format("%.2f",recentItems.cost_per_item * recentItems.qtd)}"

    }


    //inner class
    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvCodeItem = view.findViewById<TextView>(R.id.product_code)
        val tvNameItem = view.findViewById<TextView>(R.id.product_name)
        val tvUnitValue = view.findViewById<TextView>(R.id.product_un_value)
        val tvpdvTotalValue = view.findViewById<TextView>(R.id.product_total_price)
    }
}