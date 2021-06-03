package com.example.flowtrandingsystem.gui.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flowtrandingsystem.gui.adapter.ItensEstoqueAdatpter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.model.Produto
import org.jetbrains.anko.doAsync
import com.example.flowtrandingsystem.gui.api.ProdutosCall
import retrofit2.Call
import retrofit2.Response

class InventoryActivity() : AppCompatActivity() {

    lateinit var rvItens: RecyclerView
    lateinit var adapterItensEstoque: ItensEstoqueAdatpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_activity)

        doAsync {
            val http = HttpHelper()
            http.getProduct()
        }

        supportActionBar!!.hide()

        rvItens = findViewById(R.id.recycler_view_inventory_list)

        rvItens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapterItensEstoque = ItensEstoqueAdatpter(this)

        rvItens.adapter = adapterItensEstoque

        loadListaItens()

    }

    private  fun loadListaItens() {

        var listaItens: List<Produto>

        val retrofit = RetrofitApi.getRetrofit()
        val produtosCall = retrofit.create(ProdutosCall::class.java)
        
        val call = produtosCall.getProduto()

        call.enqueue(object : retrofit2.Callback<List<Produto>>{

            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                Toast.makeText(this@InventoryActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                listaItens = response.body()!!
                adapterItensEstoque.updateListaProdutos(listaItens)
            }

        })

    }

}