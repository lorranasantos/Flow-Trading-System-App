package com.example.flowtrandingsystem.gui.model

data class Logbook (
    val id: Int = 0,
    val date_of_acquisition: String = "",
    val quantity_acquired: Int = 0,
    val Product: Product = Product(),
    val lot: Lot = Lot()
)
