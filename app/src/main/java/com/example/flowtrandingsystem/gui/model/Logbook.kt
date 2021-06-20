package com.example.flowtrandingsystem.gui.model

import java.io.Serializable

data class Logbook (
    val id: Int = 0,
    val date_of_acquisition: String = "",
    val quantity_acquired: Int = 0,
    var branch: Branch = Branch(),
    val Product: Product = Product(),
    val lot: Lot = Lot()
)
