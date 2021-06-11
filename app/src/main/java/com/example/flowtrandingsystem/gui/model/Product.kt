package com.example.flowtrandingsystem.gui.model

data class Product(

    var id: Int = 0,
    var product_name: String = "",
    var total_quantity: Long = 0,
    var cost_per_item: Double = 0.0,
    var unit_of_measurement_id: Long = 0,
    var product_type_id: Long = 0,
    var company_id: Long = 0,
    var bar_code: String = ""
)

