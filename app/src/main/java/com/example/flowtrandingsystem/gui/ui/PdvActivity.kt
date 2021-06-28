package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.BarCodeAdapter
import com.example.flowtrandingsystem.gui.api.CostumerCalls
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.SaleCalls
import com.example.flowtrandingsystem.gui.model.*
import kotlinx.android.synthetic.main.add_discount_pdv.view.*
import kotlinx.android.synthetic.main.add_payment_method_pdv.view.*
import kotlinx.android.synthetic.main.client_register_pdv.view.*
import kotlinx.android.synthetic.main.pdv.*
import retrofit2.Call
import retrofit2.Response
import java.io.Serializable


open class PdvActivity : AppCompatActivity(), Serializable{

    lateinit var rvItens: RecyclerView
    lateinit var adapterItensList: BarCodeAdapter
    lateinit var editCpfClient: EditText
//    lateinit var tvDiscount: TextView

    lateinit var subTotal: TextView

    lateinit var optionMethods: Spinner

    var listProducts: ArrayList<ProductAdapter> = ArrayList<ProductAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("PDV")

        rvItens = findViewById(R.id.recycler_view_product_sale)
        subTotal = findViewById(R.id.subTotal_pdv)

        rvItens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapterItensList = BarCodeAdapter(this)

        rvItens.adapter = adapterItensList

        pdv_client_register.setOnClickListener {
            clientRegister()
        }
        pdv_add_discount.setOnClickListener {
            addDiscount()
        }
        img_camera_code.setOnClickListener {
            val scanScreen = Intent(this, ScannerActivity::class.java)
            startActivity(scanScreen)
        }
        finish_sale.setOnClickListener {
            finishSale()
        }
        add_code.setOnClickListener {
            addProductByCode()
        }
        addProductByCamera()
    }

    fun addProductByCamera() {
        val scannedCode: String = intent.getStringExtra("barCode").toString()

        if(scannedCode.isEmpty()){
            addProductByCode()
        }else{
//            Toast.makeText(this, "Adicione seus produtos!", Toast.LENGTH_SHORT).show()
        }
    }
    fun addProductByCode() {
        val prefs: SharedPreferences = this@PdvActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")

        val editCode = findViewById<EditText>(R.id.pdv_activity_product_code)
        val editQtde = findViewById<EditText>(R.id.pdv_qtde_sale)

        if (editCode.text.isEmpty()){
            Toast.makeText(this, "Insira ou escaneie  o codigo do produto", Toast.LENGTH_SHORT).show()
            }else{

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

                    val list = arrayListOf<Double>()

                    for(item in listProducts){
                        list.add((item.cost_per_item * item.qtd))

                    }

                    val cost_total = list.reduce{totalValue, currentItem -> totalValue + currentItem}.toDouble()

//                    Toast.makeText(this@PdvActivity, "Valor Recebido: ${cost_total}", Toast.LENGTH_SHORT).show()

                   subTotal.text = "$${String.format("%.2f",cost_total)}"

                }
            })
        }
    }
    fun addDiscount() {

        val alerDialog = LayoutInflater.from(this).inflate(R.layout.add_discount_pdv, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(alerDialog)
        val alertShow = dialogBuilder.show()

        alertShow.setCanceledOnTouchOutside(false)

        alerDialog.button_cancel_discount.setOnClickListener {
            alertShow.dismiss()
        }
        alerDialog.button_save_discount.setOnClickListener {
            var editDiscountPdv = alerDialog.edit_add_discount_pdv.text.toString()

            val finalDiscount = findViewById<TextView>(R.id.final_discount)

            Toast.makeText(this, "Desconto aplicado!", Toast.LENGTH_SHORT).show()

            if (editDiscountPdv.isEmpty()){
                editDiscountPdv = 0.toString()
            }else{
                finalDiscount.text = "$editDiscountPdv%"
            }
            val prefs: SharedPreferences = this@PdvActivity.getSharedPreferences(
                "preferencias",
                Context.MODE_PRIVATE
            )

            prefs.edit().putInt("DISCOUNT", finalDiscount.toString().replace("%", "").toInt()).apply()
            alertShow.dismiss()
        }
    }
    fun finishSale(){
        val prefs: SharedPreferences = this@PdvActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedBranchId = prefs.getInt("BRANCHID", 0)
        val retrivedIten = prefs.getString("iten", "Nada foi recebido")
        val retrivedDiscount = prefs.getInt("DISCOUNT", 0)

        val alerDialog = LayoutInflater.from(this).inflate(R.layout.add_payment_method_pdv, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(alerDialog)
        val alertShow = dialogBuilder.show()

        alertShow.setCanceledOnTouchOutside(false)

        optionMethods = alerDialog.findViewById(R.id.spinner_methods) as Spinner

//        var options: PaymentMethod
//        val retrofit = RetrofitApi.getRetrofit()
//        val paymentCall = retrofit.create(SaleCalls::class.java)
//        val call = paymentCall.getLogPaymentMethod("Bearer ${retrivedToken}")
//
//        call.enqueue(object : retrofit2.Callback<PaymentMethod>{
//
//            override fun onFailure(call: Call<PaymentMethod>, t: Throwable) {
//                Toast.makeText(this@PdvActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
//                Log.e("ERRO_CONEXÃO", t.message.toString())
//            }
//            override fun onResponse(call: Call<PaymentMethod>, response: Response<PaymentMethod>) {
//                options = response.body()!!

            val options = arrayOf("Debito", "Credito")

        optionMethods.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        optionMethods.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@PdvActivity, "Selecione uma opção", Toast.LENGTH_SHORT).show()
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
        }
        alerDialog.button_cancel_sale.setOnClickListener {
            alertShow.dismiss()
        }
        alerDialog.button_finish.setOnClickListener {

            var sale = Sale(payment_method_id = 1, branch_id = retrivedBranchId, items = emptyArray<ProductAdapter>())

            if (retrivedDiscount.toString() == "0" || retrivedDiscount.toString() == ""){
                sale.discount = 0
            }else{
                sale.discount = retrivedDiscount
            }

            sale.items = arrayOf<ProductAdapter>()

            val retrofit = RetrofitApi.getRetrofit()
            val saleCall = retrofit.create(SaleCalls::class.java)
            val call = saleCall.postSale(sale, "Bearer ${retrivedToken}")

            call.enqueue(object : retrofit2.Callback<Sale> {
                override fun onFailure(call: Call<Sale>, t: Throwable) {
                    Toast.makeText(
                        this@PdvActivity,
                        "Ops! Acho que ocorreu um problema.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("ERRO_CONEXÃO", t.message.toString())
                }

                override fun onResponse(call: Call<Sale>, response: Response<Sale>) {
                    sale = response.body()!!

                    Toast.makeText(this@PdvActivity, "${retrivedDiscount}  +  $sale", Toast.LENGTH_SHORT).show()
                }
            })

            alertShow.dismiss()
            finish()
            startActivity(getIntent())
        }
    }
    private fun clientRegister() {
            val prefs: SharedPreferences = this@PdvActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
            val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")

            val alerDialog = LayoutInflater.from(this).inflate(R.layout.client_register_pdv, null)
            val dialogBuilder = AlertDialog.Builder(this)
                .setView(alerDialog)
            val alertShow = dialogBuilder.show()

            alertShow.setCanceledOnTouchOutside(false)

            alerDialog.button_cancel_client_register.setOnClickListener {
                alertShow.dismiss()
            }
            alerDialog.button_save_client_register.setOnClickListener {

                editCpfClient = alerDialog.findViewById(R.id.edit_client_register_cpf)

                var costumer = Costumer()
                costumer.cpf = editCpfClient.text.toString().replace(".", "").replace("-", "")
                val retrofit = RetrofitApi.getRetrofit()
                val costumerCall = retrofit.create(CostumerCalls::class.java)
                val call = costumerCall.postCostumer(costumer, token = "Bearer ${retrivedToken}")

                call.enqueue(object : retrofit2.Callback<Costumer>{

                    override fun onFailure(call: Call<Costumer>, t: Throwable) {
                        Toast.makeText(this@PdvActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                        Log.e("ERRO_CONEXÃO", t.message.toString())
                    }
                    override fun onResponse(call: Call<Costumer>, response: Response<Costumer>) {
                        costumer = response.body()!!

                        Toast.makeText(this@PdvActivity, "Cliente do CPF: ${costumer.cpf} Cadstrado!", Toast.LENGTH_SHORT).show()

                        alertShow.dismiss()
                    }
                })
            }
        }
}
