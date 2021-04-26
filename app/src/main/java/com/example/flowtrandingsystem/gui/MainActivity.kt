package com.example.flowtrandingsystem.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.flowtrandingsystem.R
import kotlinx.android.synthetic.main.employer_register_activity.*
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_menu_activity)

        supportActionBar?.hide();

        login_activity_button.setOnClickListener{

            goToMainMenu()
        }
        login_activity_client.setOnClickListener{

            goToMainMenu()
        }
    }

    private fun goToMainMenu(){

        val menuScreen = Intent(this, MainMenuActivity::class.java)
        startActivity(menuScreen)
    }
}



