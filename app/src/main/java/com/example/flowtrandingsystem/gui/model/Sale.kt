package com.example.flowtrandingsystem.gui.model

data class Sale(
    var payment_method_id: Long = 0,
    var branch_id: Long = 0,
    var itens: ArrayList<Itens>
)