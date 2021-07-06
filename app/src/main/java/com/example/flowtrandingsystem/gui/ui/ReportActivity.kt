package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.ReportSaleAdapter
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.SaleAndPurchaseCalls
import com.example.flowtrandingsystem.gui.model.Purchase
import com.example.flowtrandingsystem.gui.model.Sale
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import kotlinx.android.synthetic.main.report.*
import retrofit2.Call
import retrofit2.Response

class ReportActivity : AppCompatActivity() {

    lateinit var adapterSales: ReportSaleAdapter
    lateinit var tvTotalSale: TextView
    lateinit var tvTotalPurchase: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Relatorios")

        tvTotalSale = findViewById(R.id.total_sales)
        tvTotalPurchase = findViewById(R.id.total_purchases)

        adapterSales = ReportSaleAdapter(this)

        loadTotalSale()
        loadTotalPurchase()
        setPieChartData()
    }

    private fun setPieChartData(){
        val xvalues = ArrayList<String>()
        xvalues.add("Compras")
        xvalues.add("Vendas")

        val pieChartEntry = ArrayList<Entry>()
        pieChartEntry.add(Entry(30f, 0))
        pieChartEntry.add(Entry(70f, 0))

        //colors
        val colorsChart = ArrayList<Int>()
        colorsChart.add(resources.getColor(R.color.primaryDark))
        colorsChart.add(resources.getColor(R.color.primary))

        //fill the chart
        val piedataSet = PieDataSet(pieChartEntry, "Compras e Vendas")

        piedataSet.colors = colorsChart

        piedataSet.sliceSpace = 2f

        val data = PieData(xvalues, piedataSet)
        pieChart.data = data

        pieChart.holeRadius = 5f


    }
    private fun loadTotalSale() {
        val prefs: SharedPreferences = this@ReportActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")

        var totalSale: List<Sale>
        val retrofit = RetrofitApi.getRetrofit()
        val reportCall = retrofit.create(SaleAndPurchaseCalls::class.java)
        val call = reportCall.getTotalSales("Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<List<Sale>> {

            override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
                Toast.makeText(
                    this@ReportActivity,
                    "Ops! Acho que ocorreu um problema.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
                totalSale = response.body()!!

                tvTotalSale.text = totalSale.size.toString()

            }
        })
    }
    private fun loadTotalPurchase() {
        val prefs: SharedPreferences = this@ReportActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")

        var totalPurchase: List<Purchase>
        val retrofit = RetrofitApi.getRetrofit()
        val reportCall = retrofit.create(SaleAndPurchaseCalls::class.java)
        val call = reportCall.getTotalPurchases("Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<List<Purchase>> {

            override fun onFailure(call: Call<List<Purchase>>, t: Throwable) {
                Toast.makeText(
                    this@ReportActivity,
                    "Ops! Acho que ocorreu um problema.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }
            override fun onResponse(call: Call<List<Purchase>>, response: Response<List<Purchase>>) {
                totalPurchase = response.body()!!

                tvTotalPurchase.text = totalPurchase.size.toString()

            }
        })
    }
}