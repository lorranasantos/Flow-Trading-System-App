package com.example.flowtrandingsystem.gui.ui
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flowtrandingsystem.gui.adapter.ItensInventoryAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.model.Logbook
import retrofit2.Call
import retrofit2.Response

class InventoryActivity : AppCompatActivity() {

    lateinit var rvItens: RecyclerView
    lateinit var adapterItensEstoque: ItensInventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        supportActionBar!!.hide()
        rvItens = findViewById(R.id.recycler_view_inventory_list)
        rvItens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapterItensEstoque = ItensInventoryAdapter(this)

        rvItens.adapter = adapterItensEstoque
        loadListaItens()
    }
    private  fun loadListaItens() {
        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@InventoryActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedId =
            prefs.getInt("ID", 0)
        val retrivedCompanyId =
            prefs.getInt("COMPANYID", 0)
        Toast.makeText(this@InventoryActivity, "Id: ${retrivedId} IdComp: ${retrivedCompanyId} Token: ${retrivedToken}", Toast.LENGTH_LONG).show()

        var listaItens: List<Product>
        val retrofit = RetrofitApi.getRetrofit()
        val produtosCall = retrofit.create(ProductCalls::class.java)
        val call = produtosCall.getProduct(retrivedCompanyId, "Bearer ${retrivedToken}")
        call.enqueue(object : retrofit2.Callback<List<Product>>{
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@InventoryActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                listaItens = response.body()!!

                var listLog =  Logbook()

                adapterItensEstoque.updateListaProdutos(listaItens)
            }
        })
    }
}