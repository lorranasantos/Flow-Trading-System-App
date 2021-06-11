package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
<<<<<<< HEAD
import com.example.flowtrandingsystem.R
=======
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.BarCodeAdapter
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
>>>>>>> 23b1629ba906d9f3cbb9c49fa4b3caecc8e36a85
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.google.gson.Gson
import kotlinx.android.synthetic.main.client_register_pdv.*
import kotlinx.android.synthetic.main.code_scanner_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class PdvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdicionarCliente: Button
    private lateinit var editCpf: EditText

    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button

    private lateinit var buttonAddDiscount: Button
    private lateinit var editDiscount: EditText

    private lateinit var dialog: AlertDialog

    private lateinit var imgCameraCode: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD

        setContentView(R.layout.pdv_activity)
=======
        setContentView(R.layout.pdv)

        doAsync {
            val http = HttpHelper()
            http.getProduct()
        }
>>>>>>> 23b1629ba906d9f3cbb9c49fa4b3caecc8e36a85

        buttonAdicionarCliente = findViewById(R.id.pdv_client_register)
        buttonAdicionarCliente.setOnClickListener(this)

        buttonAddDiscount = findViewById(R.id.pdv_add_discount)
        buttonAddDiscount.setOnClickListener(this)

        imgCameraCode = findViewById(R.id.img_camera_code)
        imgCameraCode.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.pdv_client_register){
            openClientRegister()
        }else if(v.id == R.id.pdv_add_discount) {
            openAddDiscount()
        }else{
            Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
        }
    }

<<<<<<< HEAD
=======
    private fun addCode() {

        val editCode = findViewById<EditText>(R.id.pdv_activity_product_code)

        var itemProduct: Product

        val retrofit = RetrofitApi.getRetrofit()
        val productBarCode = retrofit.create(ProductCalls::class.java)

        val call = productBarCode.getBarProduct(editCode.text.toString())

        call.enqueue(object : Callback<Product> {

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(this@PdvActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                itemProduct = response.body()!!
                BarCodeAdapter.updateListaProdutos(itemProduct)

            }
        })
    }

//    private  fun loadListaItens() {
//
//        var listaItens: List<Produto>
//
//        val retrofit = RetrofitApi.getRetrofit()
//        val produtosCall = retrofit.create(ProdutosCall::class.java)
//
//        val call = produtosCall.getProduto()
//
//        call.enqueue(object : retrofit2.Callback<List<Produto>>{
//
//            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
//                Toast.makeText(this@InventoryActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
//                Log.e("Erro_CONEXÃO", t.message.toString())
//            }
//
//            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
//                listaItens = response.body()!!
//                adapterItensEstoque.updateListaProdutos(listaItens)
//            }
//
//        })
//
//    }



>>>>>>> 23b1629ba906d9f3cbb9c49fa4b3caecc8e36a85
    private fun openAddDiscount() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_discount_pdv, null)
        alertDialog.setView(view)

        editDiscount = view.findViewById(R.id.edit_add_discount_pdv)
        buttonSave = view.findViewById(R.id.button_save_discount)
        buttonCancel = view.findViewById(R.id.button_cancel_discount)
    }

    private fun openClientRegister() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.client_register_pdv, null)
        alertDialog.setView(view)

        editCpf = view.findViewById(R.id.edit_client_register_cpf)
        buttonSave = view.findViewById(R.id.button_save_client_register)
        buttonCancel = view.findViewById(R.id.button_cancel_client_register)

        buttonSave.setOnClickListener{
            val newClient = RegisterClientPdv()

            newClient.cpf = editCpf.text.toString()

            val gson = Gson()
            val clientJson = gson.toJson(newClient)

            doAsync {
                val http = HttpHelper()
                http.postCostumer(clientJson)
            }
        }

        buttonCancel.setOnClickListener(this)

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()


    }

}