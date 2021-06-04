package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.BarCodeAdapter
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PdvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdicionarCliente: Button
    private lateinit var buttonAddDiscount: Button
    private lateinit var dialog: AlertDialog

    private lateinit var imgCameraCode: ImageView
    private lateinit var barCode: String

    private lateinit var rvProductSale: RecyclerView
    private lateinit var buttonAddCode: Button
    //private lateinit var adapterItemsSale: ItemsSaleAdapter

    lateinit var BarCodeAdapter: BarCodeAdapter

    private lateinit var scanResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv)

        doAsync {
            val http = HttpHelper()
            http.getProduct()
        }

        buttonAdicionarCliente = findViewById(R.id.pdv_client_register)
        buttonAdicionarCliente.setOnClickListener(this)

        buttonAddDiscount = findViewById(R.id.pdv_add_discount)
        buttonAddDiscount.setOnClickListener(this)

        buttonAddCode = findViewById(R.id.add_code)
        buttonAddCode.setOnClickListener(this)

        imgCameraCode = findViewById(R.id.img_camera_code)
        imgCameraCode.setOnClickListener(this)

        scanResultado = findViewById(R.id.pdv_activity_product_code)


        barCode = intent.getStringExtra("barCode").toString()

        if (barCode !== "null") {

//            Toast.makeText(this, "Codigo: ${barCode}", Toast.LENGTH_SHORT).show()
            scanResultado.setText(barCode)

        }

//        rvProductSale = findViewById(R.id.recycler_view_product_sale)
//
//        rvProductSale.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//        adapterItemsSale= ItemsSaleAdapter(this)
//        rvProductSale.adapter = adapterItemsSale

    }

//    private fun loadProductsSale(){
//
//        var listaDeCompras: List<Sale>
//
//        val retrofit = RetrofitApi.getRetrofit()
//        val saleProductCall = retrofit.create(SaleProductCall::class.java)
//
//        val call = saleProductCall.getSaleProduct()
//
//        call.enqueue(object : retrofit2.Callback<List<Sale>>{
//
//            override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
//                Toast.makeText(this@PdvActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
//                Log.e("Erro_CONEXÃO", t.message.toString())
//            }
//
//            override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
//                listaDeCompras = response.body()!!
//                adapterItemsSale.updateListSale(listaDeCompras)
//            }
//        })
//    }

    override fun onClick(v: View) {
        if (v.id == R.id.pdv_client_register){
            openClientRegister()
        }else if(v.id == R.id.pdv_add_discount) {
            openAddDiscount()
        }else if(v.id == R.id.img_camera_code) {
            toCodeBar()
        }else if(v.id == R.id.add_code) {
            addCode()
        }else{
            Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
        }
    }

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



    private fun openAddDiscount() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_discount_pdv, null)
        alertDialog.setView(view)

        val editAddDiscount = view.findViewById<EditText>(R.id.edit_add_discount_pdv)
        val buttonSave = view.findViewById<Button>(R.id.button_save_discount)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel_discount)

        buttonSave.setOnClickListener(this)

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        buttonCancel.setOnClickListener{
            dialog.dismiss()
        }


    }

    private fun openClientRegister() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.client_register_pdv, null)
        alertDialog.setView(view)

        val editRegisterCpf = view.findViewById<EditText>(R.id.edit_client_register_cpf)
        val buttonSave = view.findViewById<Button>(R.id.button_save_client_register)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel_client_register)

        buttonSave.setOnClickListener{

            val newClient = RegisterClientPdv()
            newClient.cpf = editRegisterCpf.text.toString()

            val gson = Gson()
            val clientJson = gson.toJson(newClient)

            doAsync {
                val http = HttpHelper()
                http.postCostumer(clientJson)

            }
            dialog.dismiss()
            Toast.makeText(this, "Cliente Cadastrado", Toast.LENGTH_SHORT).show()
        }

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        buttonCancel.setOnClickListener{
            dialog.dismiss()
        }

    }

    private fun toCodeBar() {
        val codeScannerScreen = Intent(this, ScannerActivity::class.java)
        startActivity(codeScannerScreen)
    }
}