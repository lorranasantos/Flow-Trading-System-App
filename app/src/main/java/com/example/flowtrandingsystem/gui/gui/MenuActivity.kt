package com.example.flowtrandingsystem.gui.gui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
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
//                R.id.nav_profile1 -> goToinventory()

            }
            true
        }

    }

//    private fun goToinventory(){
//
//        val inventoryScreen = Intent(this, MenuActivity::class.java)
//        startActivity(menuScreen)
//    }

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