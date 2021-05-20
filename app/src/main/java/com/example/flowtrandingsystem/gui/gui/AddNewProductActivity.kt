package com.example.flowtrandingsystem.gui.gui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Produto
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class AddNewProductActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_products)

        supportActionBar!!.hide()

        val buttonGravar = findViewById<Button>(R.id.products_register_button)
        val editTextNome = findViewById<EditText>(R.id.add_products_name)
        val editTextQtd = findViewById<EditText>(R.id.add_products_quantity)
        val editTextCusto = findViewById<EditText>(R.id.add_products_un_price)
        val editTextUnidade = findViewById<EditText>(R.id.add_products_measurement)
        val editTextTipo = findViewById<EditText>(R.id.add_products_type)
        val editTextCompania = findViewById<EditText>(R.id.add_products_companie)

        buttonGravar.setOnClickListener {
            // Criar um objeto produto
            val produto = Produto()
            produto.product_name = editTextNome.text.toString()
            produto.total_quantity = editTextQtd.text.toString().toInt()
            produto.cost_per_item = editTextCusto.text.toString().toDouble()
            produto.unit_of_measurement_id = editTextUnidade.text.toString().toInt()
            produto.product_type_id = editTextTipo.text.toString().toInt()
            produto.company_id = editTextCompania.text.toString().toInt()

            // COnverter o produto em json
            val gson = Gson()
            val produtoJson = gson.toJson(produto)

            doAsync {
                val http = HttpHelper()
                http.post(produtoJson)
            }

        }

    }

}