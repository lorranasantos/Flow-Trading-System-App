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
import com.example.flowtrandingsystem.gui.api.InventoryCalls
import com.example.flowtrandingsystem.gui.api.ProductCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.model.Logbook
import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.ProductAdapter
import retrofit2.Call
import retrofit2.Response

class ReportActivity : AppCompatActivity() {

    lateinit var reportSaleToday: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Relatorios")


        loadReports()
    }

    private  fun loadReports() {
        val prefs: SharedPreferences = this@ReportActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedBranchId = prefs.getInt("BRANCHID", 0)

        var report: ArrayList<ProductAdapter>
        val retrofit = RetrofitApi.getRetrofit()
        val reportCall = retrofit.create(InventoryCalls::class.java)
        val call = reportCall.getReports(retrivedBranchId, "Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<ArrayList<ProductAdapter>>{

            override fun onFailure(call: Call<ArrayList<ProductAdapter>>, t: Throwable) {
                Toast.makeText(this@ReportActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }
            override fun onResponse(call: Call<ArrayList<ProductAdapter>>, response: Response<ArrayList<ProductAdapter>>) {
                report = response.body()!!

                Toast.makeText(this@ReportActivity, "Dados: $report", Toast.LENGTH_SHORT).show()
            }
        })
    }
}