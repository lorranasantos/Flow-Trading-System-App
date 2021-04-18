package com.example.flowtrandingsystem.gui

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R

class Splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        android.os.Handler(Looper.getMainLooper()).postDelayed({

            val abrirActivityMain = Intent(this, MainActivity::class.java)
            startActivity(abrirActivityMain)
            finish()

        }, 90000 )
    }

}