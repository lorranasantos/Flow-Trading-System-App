package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var campoCpf: String
    private lateinit var campoSenha: String


    private fun goToMainMenu(){
        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.hide();

        login_activity_button.setOnClickListener(this)




    }

   override fun onClick(v: View?) {

       campoCpf = findViewById<TextInputEditText>(R.id.editTextUser).toString()
       campoSenha = findViewById<TextInputEditText>(R.id.editTextPassword).toString()

       apiClient = ApiClient()
       sessionManager = SessionManager(this)

       executarLogin()

//       apiClient.getApiService().login(LoginRequest(cnpj_ou_cpf = campoCpf, password = campoSenha))
//           .enqueue(object : Callback<LoginResponse> {
//               override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                   // Error logging in
//               }
//
//               override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                   val loginResponse = response.body()
//
//                   if (loginResponse?.user != null) {
//
//                       goToMainMenu()
//
//                   } else {
//                       toast("Falha")
//                   }
//               }
//           })



//
//        val httpHelper = HttpHelper()
//
//        doAsync {
//            val usuario = httpHelper.login(editTextUser.text.toString(), editTextPassword.text.toString())
//
//            uiThread {
//                if (usuario.cnpj_ou_cpf.isNotEmpty()) {
//                    goToMainMenu()
//                }else{
//                    toast("Falha")
//
//                }
//            }
//
//        }
//
    }

    fun executarLogin() {

        var token = Token("")

        val usuario = Usuario(
            cnpj_ou_cpf = "502.671.948-11", password = "12345678"
        )

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
            }

        })
    }

}
