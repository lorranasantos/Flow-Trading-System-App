package com.example.flowtrandingsystem.gui.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.model.Logbook
import com.example.flowtrandingsystem.gui.model.Product

class DatasheetActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datasheet)

        lateinit var tvNameProduct: TextView
        lateinit var tvType: TextView
        lateinit var tvCostPerItem: TextView
//        lateinit var tvQtd: TextView
//        lateinit var tvAcquisition: TextView
//        lateinit var tvExpiraton: TextView
        lateinit var product: Product
//        lateinit var logbook: Logbook

        tvNameProduct = findViewById(R.id.product_name_datasheet)
        tvType = findViewById(R.id.type_of_product_datasheet)
        tvCostPerItem = findViewById(R.id.value_un_price_datasheet)
//        tvQtd = findViewById(R.id.amount_of_inventory_datasheet)
//        tvAcquisition = findViewById(R.id.price_datasheet)
//        tvExpiraton = findViewById(R.id.expiration_date_datasheet)

        product = intent.getSerializableExtra("product") as Product

//        logbook = intent.getSerializableExtra("log") as Logbook

        tvNameProduct.text = product.product_name
        tvType.text = product.product_type_id.toString()
        tvCostPerItem.text = product.cost_per_item.toString()
//        tvQtd.text = logbook.quantity_acquired.toString()
//        tvAcquisition.text = logbook.lot.manufacture_date
//        tvExpiraton.text = logbook.lot.expiration_date

        if (product.cost_per_item == 0.0) {
            tvCostPerItem.text = "GRÁTIS"
        } else {
            tvCostPerItem.text = "R$ ${String.format("%.2f", product.cost_per_item)}"
        }
    }
}

