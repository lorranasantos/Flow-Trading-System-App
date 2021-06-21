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
import com.example.flowtrandingsystem.gui.model.Permissions
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.ui.DatasheetActivity

class OptionsMenuAdatpter (val context: Context) : RecyclerView.Adapter<OptionsMenuAdatpter.Holder>() {

    var listItens =  emptyList<Permissions>()

    fun updateListaProdutos(lista: List<Permissions>){
        listItens = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.holder_operation_items, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listItens.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itensRecentes = listItens[position]

        holder.tvOperationName.text = itensRecentes.permission_name

//        holder.cardInventoryItems.setOnClickListener{
//            val intent = Intent(context, DatasheetActivity::class.java)
//            intent.putExtra("screen", itensRecentes)
//            context.startActivity(intent)
//
//        }
    }

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvOperationName = view.findViewById<TextView>(R.id.operation)
//        val cardInventoryItems = view.findViewById<CardView>(R.id.card_operation_inventory)

    }
}