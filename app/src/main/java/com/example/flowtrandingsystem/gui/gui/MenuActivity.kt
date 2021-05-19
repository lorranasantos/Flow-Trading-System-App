package com.example.flowtrandingsystem.gui.gui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.MainActivity
import kotlinx.android.synthetic.main.fragment_initial_menu.*


class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inventoryCard: CardView
    private lateinit var sellCard: CardView

    lateinit var toggle: ActionBarDrawerToggle

    private fun goToInfoCompany(){

        val companyScreen = Intent(this, CompanyInfoActivity::class.java)
        startActivity(companyScreen)
    }

    private fun goToInfoUser(){

        val userScreen = Intent(this, UserInfoActivity::class.java)
    startActivity(userScreen)
}

    private fun goToLogin(){

        val loginScreen = Intent(this, MainActivity::class.java)
        startActivity(loginScreen)
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_initial_menu)

        inventoryCard = findViewById(R.id.card_inventory)
        inventoryCard.setOnClickListener(this)

        sellCard = findViewById(R.id.card_sell)
        sellCard.setOnClickListener(this)

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
    }

    override fun onClick(v: View) {
        if (v.id == R.id.card_sell) {
            val intentSell = Intent(this, SellActivity::class.java)
            startActivity(intentSell)
        } else
        if (v.id == R.id.card_inventory) {
            val intentInventory = Intent(this, InventoryActivity::class.java)
            startActivity(intentInventory)
        } else {
            Toast.makeText(this, "Nada foi clicado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
