package com.example.flowtrandingsystem.gui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R

class MainMenuActivity : AppCompatActivity() {

    /**
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        main_navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_profile1 -> Toast.makeText(applicationContext,
                    "item clicado 1", Toast.LENGTH_SHORT).show()
                R.id.nav_profile2 -> Toast.makeText(applicationContext,
                    "item clicado 2", Toast.LENGTH_SHORT).show()
                R.id.nav_profile3 -> Toast.makeText(applicationContext,
                    "item clicado 3", Toast.LENGTH_SHORT).show()
                R.id.nav_profile4 -> goToRegisterEmployer()
            }
            true
        }

        btn_register_employer_screen.setOnClickListener {

            goToRegisterEmployer()
        }
        btn_register_manager_screen.setOnClickListener {

            goToRegisterManager()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun goToRegisterEmployer() {

        val registerScreen = Intent(this, RegisterEmployerActivity::class.java)
        startActivity(registerScreen)
    }


    private fun goToRegisterManager() {

        val registerScreen = Intent(this, RegisterManagerActivity::class.java)
        startActivity(registerScreen)
    }**/
}