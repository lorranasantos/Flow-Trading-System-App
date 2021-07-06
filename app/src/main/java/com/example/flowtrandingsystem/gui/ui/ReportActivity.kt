package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.ReportSaleAdapter
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.SaleCalls
import com.example.flowtrandingsystem.gui.model.Sale
import retrofit2.Call
import retrofit2.Response

class ReportActivity : AppCompatActivity() {

    lateinit var rvReportSales: RecyclerView
    lateinit var adapterSales: ReportSaleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Relatorios")

        rvReportSales = findViewById(R.id.recycler_view_report_sale)

        rvReportSales.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapterSales = ReportSaleAdapter(this)
        rvReportSales.adapter = adapterSales

        loadReports()
    }

    private fun loadReports() {
        val prefs: SharedPreferences = this@ReportActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedBranchId = prefs.getInt("BRANCHID", 0)

        var report: List<Sale>
        val retrofit = RetrofitApi.getRetrofit()
        val reportCall = retrofit.create(SaleCalls::class.java)
        val call = reportCall.getSalesInfo(retrivedBranchId,"Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<List<Sale>>{

            override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
                Toast.makeText(this@ReportActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
                if (response.code() == 200 || response.code() == 201){
                    report = response.body()!!

                    Log.i("XPTO", response.body().toString())
                    adapterSales.updateSaleList(report)
                }else{
                    Toast.makeText(this@ReportActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}