package com.example.flowtrandingsystem.gui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import kotlinx.android.synthetic.main.main_menu_activity.*

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)

        btn_register_employer_screen.setOnClickListener {

            goToRegisterEmployer()
        }
        btn_register_manager_screen.setOnClickListener {

            goToRegisterManager()
        }
    }

    private fun goToRegisterEmployer() {

        val registerScreen = Intent(this, RegisterEmployerActivity::class.java)
        startActivity(registerScreen)
    }


    private fun goToRegisterManager() {

        val registerScreen = Intent(this, RegisterManagerActivity::class.java)
        startActivity(registerScreen)
    }
}