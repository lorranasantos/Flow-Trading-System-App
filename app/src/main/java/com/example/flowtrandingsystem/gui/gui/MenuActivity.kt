package com.example.flowtrandingsystem.gui.gui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.flowtrandingsystem.R
import kotlinx.android.synthetic.main.fragment_initial_menu.*

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inventoryCard: CardView
    private lateinit var sellCard: CardView

    lateinit var toggle: ActionBarDrawerToggle

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
                R.id.nav_profile1 -> Toast.makeText(applicationContext,
                    "item clicado 1", Toast.LENGTH_SHORT).show()
                R.id.nav_profile2 -> Toast.makeText(applicationContext,
                    "item clicado 2", Toast.LENGTH_SHORT).show()
                R.id.nav_profile3 -> Toast.makeText(applicationContext,
                    "item clicado 3", Toast.LENGTH_SHORT).show()
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
}