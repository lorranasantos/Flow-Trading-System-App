package com.example.flowtrandingsystem.gui.gui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.flowtrandingsystem.gui.adapter.ItensEstoqueAdatpter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Produto
import org.jetbrains.anko.doAsync

class InventoryActivity() : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAddProduct: ImageButton

    lateinit var rvItens: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_activity)

        doAsync {
            val http = HttpHelper()
            http.get()
        }

        buttonAddProduct = findViewById(R.id.button_add_product)
        buttonAddProduct.setOnClickListener(this)

        supportActionBar!!.hide()

        rvItens = findViewById(R.id.recycler_view_inventory_list)

        rvItens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapterItensEstoque = ItensEstoqueAdatpter(setListaItens(), this)

        rvItens.adapter = adapterItensEstoque

    }

    private  fun setListaItens() : List<Produto> {
        val lista = listOf(
            Produto("adsadada",0,0.0,0,0,0))
        return lista

    }

    override fun onClick(v:View) {
        (v.id == R.id.button_add_product)
        val intentAddProduct = Intent(this, AddNewProductActivity::class.java)
        startActivity(intentAddProduct)


    }
}