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
import com.example.flowtrandingsystem.gui.model.Produto
import com.example.flowtrandingsystem.gui.model.RegisterClientPdv
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pdv.*
import kotlinx.android.synthetic.main.client_register_pdv.*
import kotlinx.android.synthetic.main.code_scanner_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class PdvActivity : AppCompatActivity() {

    private lateinit var buttonAdicionarCliente: Button

    private lateinit var buttonAddDiscount: Button

    private lateinit var imgCameraCode: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdv)

        buttonAdicionarCliente = findViewById(R.id.pdv_client_register)
        buttonAddDiscount = findViewById(R.id.pdv_add_discount)
<<<<<<< HEAD
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

=======
>>>>>>> 31e69a1bf62f96f25a058f5cbdcdcecdd5ca9d93
    }

}