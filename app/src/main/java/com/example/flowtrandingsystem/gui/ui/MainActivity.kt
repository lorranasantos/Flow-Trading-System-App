package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.http.HttpHelper
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


class MainActivity: AppCompatActivity(), View.OnClickListener {


    private fun goToMainMenu(){
        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.hide();

        login_activity_button.setOnClickListener{

        }

    }

    override fun onClick(v: View?) {

        val httpHelper = HttpHelper()

        doAsync {
            val usuario = httpHelper.login(editTextUser.text.toString(), editTextPassword.text.toString())

            uiThread {
                if (usuario.cnpj_ou_cpf.isNotEmpty()) {
                    goToMainMenu()
                }else{
                    toast("Cu")

                }
            }

        }

    }


}
