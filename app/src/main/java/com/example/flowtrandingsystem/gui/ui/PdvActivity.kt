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

class PdvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdicionarCliente: Button
    private lateinit var editCpf: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdv)

        buttonAdicionarCliente = findViewById(R.id.pdv_client_register)
        buttonAdicionarCliente.setOnClickListener(this)
        }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.pdv_client_register) {
                openClientRegister()
            }
            else if (v != null) {
                if(v.id == R.id.button_cancel_client_register){
                    dialog.dismiss()
                } else if (v != null) {
                    if(v.id == R.id.button_save_client_register) {
                        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }

    private fun openClientRegister() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.client_register_pdv, null)
        alertDialog.setView(view)

        editCpf = view.findViewById(R.id.edit_client_register_cpf)

        buttonSave = findViewById(R.id.button_save_client_register)
        buttonSave.setOnClickListener(this)

        buttonCancel = findViewById(R.id.button_cancel_client_register)
        buttonCancel.setOnClickListener(this)

        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()
    }


}