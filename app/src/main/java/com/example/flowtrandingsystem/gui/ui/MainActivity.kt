package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.api.*
import com.example.flowtrandingsystem.gui.http.HttpHelper
import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.santalu.maskara.widget.MaskEditText
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.log


class MainActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var editTextCpf: EditText
    private lateinit var editTextSenha: EditText

    private fun goToMainMenu(){
        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.hide()

        editTextCpf = findViewById(R.id.editTextUser)
        editTextSenha = findViewById(R.id.editTextPassword)

        login_activity_button.setOnClickListener(this)

    }

   override fun onClick(v: View?) {

       executarLogin()
    }

    fun executarLogin() {

        var token: Token = Token("")

        val usuario = Usuario(
            cnpj_ou_cpf = editTextCpf.text.toString(), password = editTextSenha.text.toString()
        )

        Log.e("Usuario", usuario.toString())

        val retrofit = RetrofitApi.getRetrofit()
        val loginCall = retrofit.create(LoginCall::class.java)

        val call = loginCall.postLogin(usuario)

        call.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(this@MainActivity, "A conexão falhou :>", Toast.LENGTH_LONG).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                token = response.body()!!
                Log.e("RESPONSE", token.toString())

                goToMainMenu()
            }
        })
    }
}
