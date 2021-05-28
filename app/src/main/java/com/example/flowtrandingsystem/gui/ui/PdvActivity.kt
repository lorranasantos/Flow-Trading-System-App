package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pdv.*
import kotlinx.android.synthetic.main.client_register_pdv.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class PdvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdicionarCliente: Button
    private lateinit var editCpf: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var dialog: AlertDialog

    private lateinit var buttonAddDiscount: Button
    private lateinit var editDiscount: EditText
    private lateinit var buttonSaveDiscount: Button
    private lateinit var buttonCancelDiscount: Button

    private lateinit var imgCameraCode: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdv)

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
        }else if(v.id == R.id.img_camera_code) {
            toCodeBar()
        }else{
            Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toCodeBar() {
        val codeScannerScreen = Intent(this, ScannerActivity::class.java)
        startActivity(codeScannerScreen)
    }

    /*private fun saveDiscount() {
        dialog.dismiss()
    }*/

    private fun openAddDiscount() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_discount_pdv, null)
        alertDialog.setView(view)

        editDiscount = view.findViewById(R.id.edit_add_discount_pdv)

        buttonSaveDiscount = view.findViewById(R.id.button_save_discount)
        buttonSaveDiscount.setOnClickListener(this)

        buttonCancelDiscount = view.findViewById(R.id.button_cancel_discount)
        buttonCancelDiscount.setOnClickListener(this)

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

    }


    private fun openClientRegister() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.client_register_pdv, null)
        alertDialog.setView(view)

        editCpf = view.findViewById(R.id.edit_client_register_cpf)

        buttonSave = view.findViewById(R.id.button_save_client_register)
        buttonSave.setOnClickListener(this)

        buttonCancel = view.findViewById(R.id.button_cancel_client_register)
        buttonCancel.setOnClickListener(this)

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        edit_client_register_cpf.setOnClickListener{
            val register = RegisterClientPdv()
            register.cpf = editCpf.text.toString()

            val gson = Gson()
            val registerClientJson = gson.toJson(register)

            doAsync {
                val http = HttpHelper()
                http.post(registerClientJson)
            }

            dialog.dismiss()
        }
    }
}