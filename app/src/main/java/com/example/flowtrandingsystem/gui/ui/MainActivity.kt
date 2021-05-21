package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.flowtrandingsystem.R
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



