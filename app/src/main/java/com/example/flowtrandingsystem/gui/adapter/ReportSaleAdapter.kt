package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.ReportSale
import com.example.flowtrandingsystem.gui.model.Sale

class ReportSaleAdapter(val context: Context): RecyclerView.Adapter<ReportSaleAdapter.Holder>() {

    var listSale = emptyList<ReportSale>()

    fun updateSaleList(list: List<ReportSale>){
        listSale = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.holder_report_items, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listSale.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val recentSale = listSale[position]

        holder.itemCode.text = recentSale.bar_code.toString()
        holder.itemName.text = recentSale.product_name
        holder.itemDescription.text = recentSale.description
        holder.itemQuantity.text = recentSale.quantity.toString()
        holder.itemCost.text = recentSale.cost_per_item.toString()
        holder.itemUn.text = recentSale.unit_name

    }

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val itemCode = view.findViewById<TextView>(R.id.report_product_code)
        val itemName = view.findViewById<TextView>(R.id.report_product_name)
        val itemDescription = view.findViewById<TextView>(R.id.report_product_descripton)
        val itemQuantity = view.findViewById<TextView>(R.id.report_product_quantity)
        val itemCost = view.findViewById<TextView>(R.id.report_costs)
        val itemUn = view.findViewById<TextView>(R.id.report_un)
    }
}