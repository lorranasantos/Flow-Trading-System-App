package com.example.flowtrandingsystem.gui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R

class InventoryActivity: AppCompatActivity() {

    private lateinit var rvInventoryItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_activity)

        rvInventoryItems = findViewById(R.id.recycler_view_inventory_list)

        rvInventoryItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }
}