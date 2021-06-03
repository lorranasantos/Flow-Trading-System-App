package com.example.flowtrandingsystem.gui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.Produto
import com.example.flowtrandingsystem.gui.model.Sale

//class ItemsSaleAdapter (val context: Context): RecyclerView.Adapter<ItemsSaleAdapter.Holder>() {
//
//    var listItemsSale = emptyList<Sale>()
//
//    fun updateListSale(lista: Produto){
//        listItemsSale = lista
//        notifyDataSetChanged()
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater
//            .from(context)
//            .inflate(R.layout.holder_list_items_sale, parent, false)
//
//        return Holder(view)
//    }
//
//    override fun getItemCount():Int{
//        return listItemsSale.size
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val itensCarrinho = listItemsSale[position]
//
//        holder.tv_product_name.text = itensCarrinho.product_id.toString()
//        holder.tv_product_quantity.text = itensCarrinho.quantity.toString()
//
//    }
//
//    class Holder(view: View): RecyclerView.ViewHolder(view){
//    val tv_product_number = view.findViewById<TextView>( R.id.product_number)
//    val tv_product_code = view.findViewById<TextView>( R.id.product_code)
//    val tv_product_quantity = view.findViewById<TextView>( R.id.product_quantity)
//    val tv_product_name = view.findViewById<TextView>( R.id.product_name)
//    val tv_product_un_value = view.findViewById<TextView>( R.id.product_un_value)
//    val tv_product_total_price = view.findViewById<TextView>( R.id.product_total_price)
//    }
//}
