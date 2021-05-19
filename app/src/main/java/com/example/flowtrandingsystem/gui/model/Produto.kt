package com.example.flowtrandingsystem.gui.model

class Produto(
    s: String,
    i: Int,
    d: Double,
    i1: Int,
    i2: Int,
    i3: Int
) {
    var product_name = ""
    var total_quantity = 0
    var cost_per_item = 0.0
    var unit_of_measurement_id = 0
    var product_type_id = 0
    var company_id = 0


    override fun toString(): String {
        return "Produto(product_name='$product_name', total_quantity=$total_quantity, cost_per_item=$cost_per_item, unit_of_measurement_id=$unit_of_measurement_id, product_type_id=$product_type_id, company_id=$company_id)"
    }


}

