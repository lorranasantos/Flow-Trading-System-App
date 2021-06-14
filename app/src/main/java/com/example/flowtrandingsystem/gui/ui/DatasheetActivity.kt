package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.UserCalls
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DatasheetActivity: AppCompatActivity() {

    lateinit var productName: TextView
    lateinit var productType: TextView
    lateinit var unitPrice: TextView
    lateinit var wholesalePrice: TextView
    lateinit var quantityInInventory: TextView
    lateinit var aquisitionDate: TextView
    lateinit var expirationDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datasheet)

        loadInfo()
    }

    private  fun loadInfo() {

        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@DatasheetActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")

        val retrivedProductId =
            prefs.getInt("PRODUCTID", 0)

        var productInfo: Product

        val retrofit = RetrofitApi.getRetrofit()
        val productCall = retrofit.create(ProductCalls::class.java)

        val call = productCall.getProductById(retrivedProductId, "Bearer ${retrivedToken}")

        call.enqueue(object : Callback<Product> {

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(this@DatasheetActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                productInfo = response.body()!!

                Log.i("TESTE", response.body().toString())

                productName.text = productInfo.product_name
                productType.text = productInfo.product_type_id.toString()
                unitPrice.text = productInfo.cost_per_item.toString()


//                lateinit var quantityInInventory: TextView
//                lateinit var aquisitionDate: TextView
//                lateinit var expirationDate: TextView
//
            }


        })

    }

}