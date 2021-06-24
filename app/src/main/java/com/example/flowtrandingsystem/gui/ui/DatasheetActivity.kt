package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.example.flowtrandingsystem.gui.model.Logbook
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datasheet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Informações do Produto")

        loadInfo()

        lateinit var tvNameProduct: TextView
        lateinit var tvType: TextView
        lateinit var tvCostPerItem: TextView
        lateinit var tvQtd: TextView
        lateinit var tvAcquisition: TextView
        lateinit var tvExpiraton: TextView
        lateinit var product: Product
        var logbook: Logbook = Logbook()

        tvNameProduct = findViewById(R.id.product_name_datasheet)
        tvType = findViewById(R.id.type_of_product_datasheet)
        tvCostPerItem = findViewById(R.id.value_un_price_datasheet)
        tvQtd = findViewById(R.id.amount_of_inventory_datasheet)
        tvAcquisition = findViewById(R.id.price_datasheet)
        tvExpiraton = findViewById(R.id.expiration_date_datasheet)

        product = intent.getSerializableExtra("product") as Product
//        logbook = intent.getSerializableExtra("log") as Logbook

        tvNameProduct.text = product.product_name
        tvType.text = product.product_type_id.toString()
        tvCostPerItem.text = product.cost_per_item.toString()
        tvQtd.text = logbook.quantity_acquired.toString()
        tvAcquisition.text = logbook.date_of_acquisition
        tvExpiraton.text = logbook.lot.expiration_date

        if (product.cost_per_item == 0.0) {
            tvCostPerItem.text = "GRÁTIS"
        } else {
            tvCostPerItem.text = "R$ ${String.format("%.2f", product.cost_per_item)}"
        }
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



