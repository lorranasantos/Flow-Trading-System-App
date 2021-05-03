package com.example.flowtrandingsystem.gui.gui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R

class InventoryActivity() : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAddProduct: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_activity)

        buttonAddProduct = findViewById(R.id.button_add_product)
        buttonAddProduct.setOnClickListener(this)

        supportActionBar!!.hide()
    }
    override fun onClick(v:View) {
        (v.id == R.id.button_add_product)
        val intentAddProduct = Intent(this, AddNewProductActivity::class.java)
        startActivity(intentAddProduct)


    }
}