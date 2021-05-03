package com.example.flowtrandingsystem.gui.gui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.flowtrandingsystem.R

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inventoryCard: CardView
    private lateinit var sellCard: CardView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_initial_menu)

        inventoryCard = findViewById(R.id.card_inventory)
        inventoryCard.setOnClickListener(this)

        sellCard = findViewById(R.id.card_sell)
        sellCard.setOnClickListener(this)

        supportActionBar!!.hide()
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