package com.example.flowtrandingsystem.gui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R

class LoginActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var loginUser: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginEnter: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        loginUser = findViewById(R.id.login_activity_user)
        loginUser.setOnClickListener(this)

        loginPassword = findViewById(R.id.login_activity_password)
        loginPassword.setOnClickListener(this)

        loginUser = findViewById(R.id.login_activity_button)
        loginUser.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented não tem a próxima tela ainda")
    }
}