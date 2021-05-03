package com.example.flowtrandingsystem.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.gui.MenuActivity
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity: AppCompatActivity() {

    private lateinit var menuFragment: Fragment
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.hide();

        login_activity_button.setOnClickListener{

            goToMainMenu()
        }
    }

    private fun goToMainMenu(){

        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)
    }

}



