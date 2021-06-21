package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.ProductType

class ProductsCategoriesAdapter (val context: Context): RecyclerView.Adapter<ProductsCategoriesAdapter.Holder>() {

    var listCategories = emptyList<ProductType>()

    fun updateCategoryList(list: List<ProductType>){
        listCategories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCategoriesAdapter.Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.holder_inventory_items, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listCategories.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val recentCategories = listCategories[position]


        holder.productCategory.text = recentCategories.type
    }

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val productCategory = view.findViewById<TextView>(R.id.name_product_type)
    }
}