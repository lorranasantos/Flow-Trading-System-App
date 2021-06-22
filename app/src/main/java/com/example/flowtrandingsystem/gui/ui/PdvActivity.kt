package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.BarCodeAdapter
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.SaleCalls
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.ProductAdapter
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.example.flowtrandingsystem.gui.model.Sale
import com.google.gson.Gson
import kotlinx.android.synthetic.main.pdv.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Response


class PdvActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var rvItens: RecyclerView
    lateinit var adapterItensList: BarCodeAdapter
    private lateinit var buttonAddClient: Button
    private lateinit var editCpf: EditText
    private lateinit var buttonSaveClient: Button
    private lateinit var buttonCancelClient: Button
    private lateinit var buttonAddDiscount: Button
    private lateinit var editDiscount: EditText
    private lateinit var imgCameraCode: ImageView
    private lateinit var buttonAddCode: Button
    private lateinit var buttonFinishSale: Button

    var listProducts: ArrayList<ProductAdapter> = ArrayList<ProductAdapter>()

    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv)

        val et = findViewById<EditText>(R.id.pdv_qtde_sale)
        val theText = et.text.toString()



        rvItens = findViewById(R.id.recycler_view_product_sale)

        rvItens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        adapterItensList = BarCodeAdapter(this)

        rvItens.adapter = adapterItensList

        buttonAddClient = findViewById(R.id.pdv_client_register)
        buttonAddClient.setOnClickListener(this)

        buttonAddDiscount = findViewById(R.id.pdv_add_discount)
        buttonAddDiscount.setOnClickListener(this)

        imgCameraCode = findViewById(R.id.img_camera_code)
        imgCameraCode.setOnClickListener(this)

        buttonAddCode = findViewById(R.id.add_code)
        buttonAddCode.setOnClickListener(this)

        buttonFinishSale = findViewById(R.id.finish_sale)
        buttonFinishSale.setOnClickListener(this)

        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@PdvActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")

        Toast.makeText(this@PdvActivity, "RETRIEVED: ${retrivedToken}", Toast.LENGTH_LONG).show()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.pdv_client_register){
            openClientRegister()
        }else if(v.id == R.id.pdv_add_discount) {
            openAddDiscount()
        }else if(v.id == R.id.img_camera_code) {
            val scanScreen = Intent(this, ScannerActivity::class.java)
            startActivity(scanScreen)
        }else if(v.id == R.id.add_code) {
            addProductByCode()
        }else if(v.id == R.id.finish_sale) {
            finishSale()
        }else{
            Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addProductByCode() {

        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@PdvActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")

        val editCode = findViewById<EditText>(R.id.pdv_activity_product_code)
        val editQtde = findViewById<EditText>(R.id.pdv_qtde_sale)

        var itemProduct: ProductAdapter

        val retrofit = RetrofitApi.getRetrofit()
        val productBarCode = retrofit.create(ProductCalls::class.java)

        val call = productBarCode.getBarProduct(editCode.text.toString(), "Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<ProductAdapter>{

            override fun onFailure(call: Call<ProductAdapter>, t: Throwable) {
                Toast.makeText(this@PdvActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<ProductAdapter>, response: Response<ProductAdapter>) {
                itemProduct = response.body()!!

                var quantity = 1

                if(editQtde.text.isNotEmpty()) {
                    quantity = editQtde.text.toString().toInt()
                }

                val itemTotalValue = quantity * itemProduct.cost_per_item

                val prefs: SharedPreferences = this@PdvActivity.getSharedPreferences(
                    "total",
                    Context.MODE_PRIVATE
                )

                prefs.edit().putFloat("total", itemTotalValue.toFloat()).apply()
                prefs.edit().putString("iten", itemProduct.toString()).apply()

                itemProduct.qtd = quantity

                listProducts.add(itemProduct)

                val codeIntent = Intent(this@PdvActivity, BarCodeAdapter::class.java)
                codeIntent.putExtra("qtd", quantity)

                adapterItensList.updateListProducts(listProducts.toList())

                Toast.makeText(this@PdvActivity, "Numero Item: ${itemProduct.id} " +
                        "Qtde: ${quantity} " +
                        "Produto: ${itemProduct.product_name} " +
                        "Valor Unitario: ${itemProduct.cost_per_item} " +
                        "Valor Total: ${itemTotalValue}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun finishSale() {

        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@PdvActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")

        val retrivedIten =
            prefs.getString("iten", "Nada foi recebido")

        var sale: Sale = Sale(itens = retrivedIten.toString())

        val retrofit = RetrofitApi.getRetrofit()
        val saleCall = retrofit.create(SaleCalls::class.java)

        val call = saleCall.postSale(sale, "Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<Sale>{

            override fun onFailure(call: Call<Sale>, t: Throwable) {
                Toast.makeText(this@PdvActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<Sale>, response: Response<Sale>) {
                sale = response.body()!!

                Toast.makeText(this@PdvActivity, "Venda: ${sale}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openAddDiscount() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_discount_pdv, null)
        alertDialog.setView(view)

        editDiscount = view.findViewById(R.id.edit_add_discount_pdv)
        buttonSaveClient = view.findViewById(R.id.button_save_discount)
        buttonCancelClient = view.findViewById(R.id.button_cancel_discount)
    }

    private fun openClientRegister() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.client_register_pdv, null)
        alertDialog.setView(view)

        editCpf = view.findViewById(R.id.edit_client_register_cpf)
        buttonSaveClient = view.findViewById(R.id.button_save_client_register)
        buttonCancelClient = view.findViewById(R.id.button_cancel_client_register)

        buttonSaveClient.setOnClickListener{
            val newClient = RegisterClientPdv()

            newClient.cpf = editCpf.text.toString()

            val gson = Gson()
            val clientJson = gson.toJson(newClient)

            doAsync {
                val http = HttpHelper()
                http.postCostumer(clientJson)
            }
        }

        buttonCancelClient.setOnClickListener(this)

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()


    }

}

