package com.example.flowtrandingsystem.gui.model

import java.io.Serializable

data class Product(
    var id: Int = 0,
    var product_name: String = "",
    var description: String = "",
    var cost_per_item: Double = 0.0,
    var unit_of_measurement_id: Int = 0,
    var product_type_id: Int = 0,
    var company_id: Int = 0,
    var bar_code: String = ""
)  : Serializable

