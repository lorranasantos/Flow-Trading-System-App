package com.example.flowtrandingsystem.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.flowtrandingsystem.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        buttonLogin = findViewById(R.id.welcome_screen_button_login)
        buttonLogin.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.welcome_screen_button_login) {
            val intentIMC = Intent(this, LoginActivity::class.java)
            startActivity(intentIMC)
        } else {
        Toast.makeText(this, "Nothing here", Toast.LENGTH_SHORT).show()
    }
    }
}