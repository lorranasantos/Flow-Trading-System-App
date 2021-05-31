package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class PdvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdicionarCliente: Button

    private lateinit var buttonAddDiscount: Button

    private lateinit var dialog: AlertDialog

    private lateinit var imgCameraCode: ImageView
    private lateinit var barCode: String


    private lateinit var scanResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdv)

        buttonAdicionarCliente = findViewById(R.id.pdv_client_register)
        buttonAdicionarCliente.setOnClickListener(this)

        buttonAddDiscount = findViewById(R.id.pdv_add_discount)
        buttonAddDiscount.setOnClickListener(this)

        imgCameraCode = findViewById(R.id.img_camera_code)
        imgCameraCode.setOnClickListener(this)

        scanResultado = findViewById(R.id.pdv_activity_product_code)


        barCode = intent.getStringExtra("barCode").toString()

        if (barCode !== "null") {

//            Toast.makeText(this, "Codigo: ${barCode}", Toast.LENGTH_SHORT).show()
            scanResultado.setText(barCode)

        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.pdv_client_register){
            openClientRegister()
        }else if(v.id == R.id.pdv_add_discount) {
            openAddDiscount()
        }else if(v.id == R.id.img_camera_code) {
            toCodeBar()
        }else{
            Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
        }
    }

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