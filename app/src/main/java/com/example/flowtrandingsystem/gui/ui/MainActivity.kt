package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.UserCalls
import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.UserLogin
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity: AppCompatActivity() {

    private lateinit var editTextCpf: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var token: Token

    private fun goToMainMenu(){
        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        supportActionBar?.hide()

        editTextCpf = findViewById(R.id.editTextUser)
        editTextSenha = findViewById(R.id.editTextPassword)

        login_activity_button.setOnClickListener {

            executarLogin()
        }

    }

    fun executarLogin() {

        val usuario = UserLogin(
            cnpj_ou_cpf = editTextCpf.text.toString(), password = editTextSenha.text.toString().replace(".", "").replace("-", "")
        )

        Log.e("Usuario", usuario.toString())

        val retrofit = RetrofitApi.getRetrofit()
        val loginCall = retrofit.create(UserCalls::class.java)

        val call = loginCall.postLogin(usuario)

        call.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(this@MainActivity, "A conexão falhou :>", Toast.LENGTH_LONG).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) =
                if (response.body() != null) {
                    token = response.body()!!
                    Log.e("RESPONSE", token.toString())

                    //colocar o token no sharedPreferences

                    val prefs: SharedPreferences = this@MainActivity.getSharedPreferences(
                        "preferencias",
                        Context.MODE_PRIVATE
                    )

                    prefs.edit().putString("TOKEN", token.token).apply()
                    prefs.edit().putInt("ID", token.user.id).apply()
                    prefs.edit().putInt("COMPANYID", token.user.branch.company_id).apply()

                    goToMainMenu()

                }else {
                    Toast.makeText(this@MainActivity, "CPF ou senha invalidos", Toast.LENGTH_LONG).show()
                }
        })
    }
}
