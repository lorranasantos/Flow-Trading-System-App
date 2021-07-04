package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.ReportSaleAdapter
import com.example.flowtrandingsystem.gui.api.InventoryCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.SaleCalls
import com.example.flowtrandingsystem.gui.model.ProductAdapter
import com.example.flowtrandingsystem.gui.model.Sale
import retrofit2.Call
import retrofit2.Response

class ReportActivity : AppCompatActivity() {

    lateinit var reportSales: TextView
    lateinit var adapterSales: ReportSaleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Relatorios")

        reportSales = findViewById(R.id.report_sale)

        adapterSales = ReportSaleAdapter(this)

        loadReports()
    }

    private  fun loadReports() {
        val prefs: SharedPreferences = this@ReportActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedBranchId = prefs.getInt("BRANCHID", 0)

        var report: List<Sale>
        val retrofit = RetrofitApi.getRetrofit()
        val reportCall = retrofit.create(SaleCalls::class.java)
        val call = reportCall.getSales("Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<List<Sale>>{

            override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
                Toast.makeText(this@ReportActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
                report = response.body()!!

                adapterSales.updateSaleList(report)
            }
        })
    }
}