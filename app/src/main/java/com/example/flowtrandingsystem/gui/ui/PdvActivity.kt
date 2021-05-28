package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Produto
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.google.gson.Gson
import kotlinx.android.synthetic.main.client_register_pdv.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class PdvActivity : AppCompatActivity() {

    private lateinit var buttonAdicionarCliente: Button

    private lateinit var buttonAddDiscount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdv)

        buttonAdicionarCliente = findViewById(R.id.pdv_client_register)
        buttonAddDiscount = findViewById(R.id.pdv_add_discount)
    }

}