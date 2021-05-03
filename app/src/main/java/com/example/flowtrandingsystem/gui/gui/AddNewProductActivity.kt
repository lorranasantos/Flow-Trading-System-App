package com.example.flowtrandingsystem.gui.gui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R

class AddNewProductActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_products)
        supportActionBar!!.hide()
    }

}