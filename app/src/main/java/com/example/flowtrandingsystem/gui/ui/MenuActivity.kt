package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.flowtrandingsystem.R
import kotlinx.android.synthetic.main.fragment_initial_menu.*


class MenuActivity : AppCompatActivity() {
    private lateinit var inventoryOption: TextView
    private lateinit var pdvOption: TextView

    private lateinit var sessionToken: String
    private var sessionId: Int = 0

    lateinit var toggle: ActionBarDrawerToggle

    private fun goToInfoCompany(){

        val companyScreen = Intent(this, CompanyInfoActivity::class.java)
        startActivity(companyScreen)
    }

    private fun goToLogin(){

        val loginScreen = Intent(this, MainActivity::class.java)
        startActivity(loginScreen)

    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(drawerLayout)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_initial_menu)

        inventoryOption = findViewById(R.id.option_inventory)
        pdvOption = findViewById(R.id.option_sell)

        inventoryOption.setOnClickListener {
            val intentInventory = Intent(this, InventoryActivity::class.java)
            startActivity(intentInventory)
        }
        pdvOption.setOnClickListener{
            val intentSell = Intent(this, PdvActivity::class.java)
            startActivity(intentSell)
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        main_navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_option_home -> Toast.makeText(this, "Tela Atual", Toast.LENGTH_SHORT).show()
                R.id.nav_option_profile -> goToInfoUser()
                R.id.nav_option_company -> goToInfoCompany()
                R.id.nav_option_logout -> goToLogin()
            }
            true
        }

        sessionToken = intent.getStringExtra("token").toString()
        sessionId = intent.getIntExtra("tokenId", 0).toString().toInt()

        Log.e("RESPONSE", "Id: ${sessionId} Token: ${sessionToken}")

    }
    private fun goToInfoUser(){
        val userScreen = Intent(this, UserInfoActivity::class.java)
        userScreen.putExtra("token", sessionToken)
        userScreen.putExtra("tokenId", sessionId)
        startActivity(userScreen)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
