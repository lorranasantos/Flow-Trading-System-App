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
import com.example.flowtrandingsystem.gui.model.Logbook
import com.example.flowtrandingsystem.gui.model.ProductAdapter
import com.example.flowtrandingsystem.gui.ui.DatasheetActivity

class ItensInventoryAdapter (val context: Context) : RecyclerView.Adapter<ItensInventoryAdapter.Holder>() {

    var listItens =  emptyList<ProductAdapter>()
    var listLog =  Logbook()

    fun updateListaProdutos(lista: List<ProductAdapter>){
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
//        val logsRecentes = listLog

        holder.tvNomeItem.text = itensRecentes.product_name
        holder.tvQuantidade.text = itensRecentes.LogBookInventory.quantity_acquired.toString()
        holder.tvValor.text = itensRecentes.cost_per_item.toString()

        if(itensRecentes.cost_per_item <= 0){
            holder.tvValor.text = "GRÁTIS"
        }else {
            holder.tvValor.text = "R$ ${String.format("%.2f", itensRecentes.cost_per_item)}"
        }

        holder.cardInventoryItems.setOnClickListener{
            val intent = Intent(context, DatasheetActivity::class.java)
            intent.putExtra("productId", itensRecentes.id)
            context.startActivity(intent)

//            val prefs: SharedPreferences = this@ItensInventoryAdapter.getSharedPreferences(
//                "preferenciasLog",
//                Context.MODE_PRIVATE
//            )
//            prefs.edit().putString("logbook", logsRecentes.toString()).apply()

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