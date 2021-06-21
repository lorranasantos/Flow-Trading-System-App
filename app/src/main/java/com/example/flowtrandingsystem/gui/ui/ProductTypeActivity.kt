package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.ProductsCategoriesAdapter

class ProductTypeActivity : AppCompatActivity() {

    lateinit var rvProductCategories: RecyclerView
    lateinit var adapterProductsCategories: ProductsCategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_type)

        rvProductCategories = findViewById(R.id.recycler_view_category_list)
        rvProductCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapterProductsCategories = ProductsCategoriesAdapter(this)

        rvProductCategories.adapter = adapterProductsCategories
        loadCategoriesList()
    }

    private fun loadCategoriesList() {

        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@ProductTypeActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedId =
            prefs.getInt("ID", 0)
        val retrivedCompanyId =
            prefs.getInt("COMPANYID", 0)
        Toast.makeText(this@ProductTypeActivity, "Id: ${retrivedId} IdComp: ${retrivedCompanyId}  Token: ${retrivedToken}", Toast.LENGTH_LONG).show()

    }
}