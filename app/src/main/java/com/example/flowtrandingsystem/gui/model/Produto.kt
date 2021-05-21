package com.example.flowtrandingsystem.gui.model

class Produto() {
    var product_name: String = ""
    var total_quantity: Int = 0
    var cost_per_item: Double = 0.0
    var unit_of_measurement_id: Int = 0
    var product_type_id: Int = 0
    var company_id: Int = 0


    override fun toString(): String {
        return "Produto(product_name='$product_name', total_quantity=$total_quantity, cost_per_item=$cost_per_item, unit_of_measurement_id=$unit_of_measurement_id, product_type_id=$product_type_id, company_id=$company_id)"
    }

}

